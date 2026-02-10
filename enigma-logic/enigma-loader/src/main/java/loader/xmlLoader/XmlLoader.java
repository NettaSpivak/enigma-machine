package loader.xmlLoader;

import loader.generated.BTEEnigma;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

public class XmlLoader {

    public static BTEEnigma loadXml(String filePath) throws RuntimeException{
        try{
            if (filePath == null || filePath.isBlank()) {
                throw new IllegalArgumentException("XML file path cannot be empty");
            }

            if (!filePath.toLowerCase().endsWith(".xml")) {
                throw new IllegalArgumentException("File must be an XML file (ending with .xml)");
            }
            JAXBContext jaxbContext = JAXBContext.newInstance(BTEEnigma.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            BTEEnigma enigma = (BTEEnigma) jaxbUnmarshaller.unmarshal(new File(filePath));
            return enigma;
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to load Enigma configuration from XML: " + filePath, e);
        }
    }

}
