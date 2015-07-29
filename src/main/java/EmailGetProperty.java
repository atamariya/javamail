import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailGetProperty {

	public Properties getProp() throws IOException {
		Properties props = new Properties();
		InputStream input = null;

		try {

			// loading config.property file
			input = getClass().getClassLoader().getResourceAsStream("config.properties");
			if (input != null) {
				props.load(input);

			}
			else{
				throw new FileNotFoundException("property file not found");
			}
		} catch (IOException e) {
			e.printStackTrace();

		}
		finally
		{
			input.close();
		}
		return props;

	}
}
