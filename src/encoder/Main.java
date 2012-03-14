package encoder;

import java.io.File;

import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.engine.application.Encoder;
import org.restlet.resource.Directory;
import org.restlet.routing.Template;
import org.restlet.service.EncoderService;

public class Main
{
	public static void main( String[] args ) throws Exception
	{
		Component component = new Component();
		component.getServers().add( Protocol.HTTP, 8080 );
		component.getClients().add( Protocol.FILE );
		Directory directory = new Directory( component.getContext().createChildContext(), new File( "resources" ).toURI().toString() );
		Encoder encoder = new Encoder( component.getContext().createChildContext(), true, true, new EncoderService() );
		encoder.setNext( directory );
		component.getDefaultHost().attach( "/", encoder ).setMatchingMode( Template.MODE_STARTS_WITH );
		component.start();
	}
}
