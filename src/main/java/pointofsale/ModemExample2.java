package pointofsale;

import java.io.IOException;
import java.net.Socket;

class InfrastructureProblem extends Exception {
  public InfrastructureProblem() {
  }

  public InfrastructureProblem(String message) {
    super(message);
  }

  public InfrastructureProblem(String message, Throwable cause) {
    super(message, cause);
  }

  public InfrastructureProblem(Throwable cause) {
    super(cause);
  }

  public InfrastructureProblem(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}

public class ModemExample2 {
  public static void sellSomething() {
    boolean success = false;
    int retries = 3;
    while (retries > 0) {
      try {
        getPaid();
        success = true;
      } catch (InfrastructureProblem e) {
        System.out.println("Uh oh, there's an infrastructure problem...");
      }
    }
    if (! success) {
      System.out.println("YOu can't have that");
    }
  }

  private static boolean use_modem = true;
  public static void getPaid() throws InfrastructureProblem {
    try {
      if (use_modem) {
        ModemHandler.dialModem("800givememoney");
        // process communication to get paid..
      } else {
        Socket s = new Socket("127.0.0.1", 8000);
      }
    } catch (ModemDidNotConnectException | IOException e) {
      throw new InfrastructureProblem(e);
    }
  }
}
