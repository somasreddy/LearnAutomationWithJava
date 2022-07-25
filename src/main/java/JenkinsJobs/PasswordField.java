package JenkinsJobs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.util.Arrays;

/*
 * public class PasswordField {
 * 
 *//**
	 * @param prompt The prompt to display to the user
	 * @return The password as entered by the user
	 *//*
		 * public static String readPassword (String prompt) { EraserThread et = new
		 * EraserThread(prompt); Thread mask = new Thread(et); mask.start();
		 * 
		 * BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		 * String password = "";
		 * 
		 * try { password = in.readLine(); } catch (IOException ioe) {
		 * ioe.printStackTrace(); } // stop masking et.stopMasking(); // return the
		 * password entered by the user return password; } }
		 */


public class PasswordField {

	  /**
	   *@param input stream to be used (e.g. System.in)
	   *@param prompt The prompt to display to the user.
	   *@return The password as entered by the user.
	   */

	   public static final char[] getPassword(InputStream in, String prompt) throws IOException {
	      MaskingThread maskingthread = new MaskingThread(prompt);
	      Thread thread = new Thread(maskingthread);
	      thread.start();
		
	      char[] lineBuffer;
	      char[] buf;
	      int i;

	      buf = lineBuffer = new char[128];

	      int room = buf.length;
	      int offset = 0;
	      int c;

	      loop:   while (true) {
	         switch (c = in.read()) {
	            case -1:
	            case '\n':
	               break loop;

	            case '\r':
	               int c2 = in.read();
	               if ((c2 != '\n') && (c2 != -1)) {
	                  if (!(in instanceof PushbackInputStream)) {
	                     in = new PushbackInputStream(in);
	                  }
	                  ((PushbackInputStream)in).unread(c2);
	                } else {
	                  break loop;
	                }

	                default:
	                   if (--room < 0) {
	                      buf = new char[offset + 128];
	                      room = buf.length - offset - 1;
	                      System.arraycopy(lineBuffer, 0, buf, 0, offset);
	                      Arrays.fill(lineBuffer, ' ');
	                      lineBuffer = buf;
	                   }
	                   buf[offset++] = (char) c;
	                   break;
	         }
	      }
	      maskingthread.stopMasking();
	      if (offset == 0) {
	         return null;
	      }
	      char[] ret = new char[offset];
	      System.arraycopy(buf, 0, ret, 0, offset);
	      Arrays.fill(buf, ' ');
	      return ret;
	   }
	}