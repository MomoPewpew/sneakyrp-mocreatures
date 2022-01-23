package javax.xml.namespace;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;




































































public class QName
  implements Serializable
{
  private static final String emptyString = "".intern();



  private String namespaceURI;



  private String localPart;



  private String prefix;



  public QName(String localPart) {
    this(emptyString, localPart, emptyString);
  }







  public QName(String namespaceURI, String localPart) {
    this(namespaceURI, localPart, emptyString);
  }








  public QName(String namespaceURI, String localPart, String prefix) {
    this.namespaceURI = (namespaceURI == null) ? emptyString : namespaceURI.intern();


    if (localPart == null) {
      throw new IllegalArgumentException("invalid QName local part");
    }
    this.localPart = localPart.intern();


    if (prefix == null) {
      throw new IllegalArgumentException("invalid QName prefix");
    }
    this.prefix = prefix.intern();
  }







  public String getNamespaceURI() {
    return this.namespaceURI;
  }






  public String getLocalPart() {
    return this.localPart;
  }






  public String getPrefix() {
    return this.prefix;
  }







  public String toString() {
    return (this.namespaceURI == emptyString) ? this.localPart : ('{' + this.namespaceURI + '}' + this.localPart);
  }























  public final boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof QName)) {
      return false;
    }

    if (this.namespaceURI == ((QName)obj).namespaceURI && this.localPart == ((QName)obj).localPart)
    {
      return true;
    }

    return false;
  }


















  public static QName valueOf(String s) {
    if (s == null || s.equals("")) {
      throw new IllegalArgumentException("invalid QName literal");
    }

    if (s.charAt(0) == '{') {
      int i = s.indexOf('}');

      if (i == -1) {
        throw new IllegalArgumentException("invalid QName literal");
      }

      if (i == s.length() - 1) {
        throw new IllegalArgumentException("invalid QName literal");
      }
      return new QName(s.substring(1, i), s.substring(i + 1));
    }

    return new QName(s);
  }










  public final int hashCode() {
    return this.namespaceURI.hashCode() ^ this.localPart.hashCode();
  }






  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    in.defaultReadObject();

    this.namespaceURI = this.namespaceURI.intern();
    this.localPart = this.localPart.intern();
    this.prefix = this.prefix.intern();
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\javax\xml\namespace\QName.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
