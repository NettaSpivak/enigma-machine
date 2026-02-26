package loader.xmlLoader;

import loader.generated.BTEEnigma;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.InputStream;

public class XmlLoader {

    public static BTEEnigma loadXml(InputStream inputStream) throws IllegalArgumentException {
        if (inputStream == null) {
            throw new IllegalArgumentException("Uploaded file cannot be empty");
        }
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(BTEEnigma.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            BTEEnigma enigma = (BTEEnigma) jaxbUnmarshaller.unmarshal(inputStream);
            return enigma;
        } catch (JAXBException e) {
            throw new IllegalArgumentException("Invalid XML structure: " + e.getMessage(), e);
        }
    }

}
