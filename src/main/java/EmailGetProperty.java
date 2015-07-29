import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailGetProperty {

	public Properties getProp() throws IOException {
		Properties props = new Properties();
		InputStream input = null;

		try {

			// loading config.property file
			input = new FileInputStream("src/main/resources/config.properties");
			if (input != null) {
				props.load(input);

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
