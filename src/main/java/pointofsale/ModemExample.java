package pointofsale;

import java.io.IOException;
import java.net.Socket;

class ModemDidNotConnectException extends Exception {}

class ModemHandler {
  public static void dialModem(String number) throws ModemDidNotConnectException {

  }
}
public class ModemExample {
  public static void sellSomething() {
    // determine price

    boolean success = false;
    int retries = 3;
    while (retries > 0) {
      // get paid
      try {
        getPaid();
        success = true;
      } catch (ModemDidNotConnectException | IOException e) {
        System.out.println("Uh oh, there's an infrastructure problem...");
        // throw e only requires "throws Modem....Exception, IOException
        // i.e. method remains specific about failure modes
      }

//      } catch (Exception mdnce) {
//        System.out.println("Uh oh, there's an infrastructure problem...");
      // older java -> "throw e" would mandate method throws Exception
//      }

//      } catch (ModemDidNotConnectException mdnce) {
//        System.out.println("Uh oh, there's an infrastructure problem...");
//      } catch (IOException mdnce) {
//        System.out.println("Uh oh, there's an infrastructure problem...");
//      }
    }
    if (! success) {
      System.out.println("YOu can't have that");
    }
  }

  private static boolean use_modem = true;
  public static void getPaid() throws ModemDidNotConnectException, IOException {
    if (use_modem) {
      ModemHandler.dialModem("800givememoney");
      // process communication to get paid..
    } else {
      Socket s = new Socket("127.0.0.1", 8000);
    }
  }
}
