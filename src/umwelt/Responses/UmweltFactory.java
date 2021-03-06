package umwelt.Responses;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;

import dasBoot.Requests.iRequest;
import dasBoot.Responses.iFactory;
import dasBoot.Responses.iResponse;


public class UmweltFactory implements iFactory {
  protected Hashtable<String, String> MIMETypes;
  protected iResponse response;
  protected String DIR;

  public UmweltFactory(String dir) {
    MIMETypes = new Hashtable<String, String>();
    setMIMETypes();
    DIR = dir;
  }

/*____________________________________GET____________________________________ */

  public iResponse get(iRequest request) throws IOException {
    return get(request.uri());
  }

  public iResponse get(String request) throws IOException {
    response = new UmweltResponse();
    set200();
    response.setHeader("Content-Type", getMIMEType(extend(request)));
    getFile(extend(request));
    return response;
  }

  protected void set200() {
    response.setStatus("200", "OK");
    response.setVersion("HTTP/1.1");
  }

  public void getFile(String file) throws IOException {
    try {
      response.setContent(bytify(file)); }
    catch (Exception e) {
      FileNotFound();
    }
  }

/*____________________________________404____________________________________ */

  public iResponse FileNotFound() throws IOException {
    response = new UmweltResponse();
    set404();
    response.setHeader("Content-Type", "text/html");
    assign404File(DIR + "/404.html");
    return response;
  }

  protected void set404() {
    response.setStatus("404", "Not Found");
    response.setVersion("HTTP/1.1");
  }

  protected void assign404File(String file) {
    try {
      response.setContent(bytify(file)); }
    catch (Exception e) {
      response.setContent("404 File Not Found"); }
  }

/*_____________________________Method Not Allowed____________________________ */

  public iResponse MethodNotAllowed() throws IOException {
    response = new UmweltResponse();
    set405();
    response.setHeader("Content-Type", "text/html");
    assign405File(DIR + "/405.html");
    return response;
  }

  protected void set405() {
    response.setStatus("405", "Method Not Allowed");
    response.setVersion("HTTP/1.1");
  }

  protected void assign405File(String file) {
    try {
      response.setContent(bytify(file)); }
    catch (Exception e) {
      response.setContent("405 Method Not Allowed"); }
  }
/*___________________________________________________________________________ */

  protected byte[] bytify(String path) throws IOException {
    Path file = Paths.get(path);
    return Files.readAllBytes(file);
  }

  protected String extend(String uri) {
    String filepath = DIR + uri;
    return (new File(filepath + ".html").exists()) ? filepath + ".html" : filepath;
  }

  public String getMIMEType(String filename) {
    String extension = getExtensions(filename);
    if(MIMETypes.containsKey(extension)) {
      return MIMETypes.get(extension);
    } else {
      return "text/plain";
    }
  }

  protected String getExtensions(String filename) {
    try
      { return filename.substring(filename.lastIndexOf(".")); }
    catch (StringIndexOutOfBoundsException e)
      {  return ".txt"; }
  }

  protected void setMIMETypes() {
    MIMETypes.put(  ".js", "text/javascript");
    MIMETypes.put(".html", "text/html");
    MIMETypes.put( ".png", "image/png");
    MIMETypes.put( ".jpg", "image/jpg");
    MIMETypes.put( ".css", "text/css");
    MIMETypes.put( ".txt", "text/plain");
  }
}
