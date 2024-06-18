package de.fhws.fiw.fds.Project3.server.api;

import de.fhws.fiw.fds.Project3.server.api.models.Partner;
import java.util.Comparator;

public class PartnerComparator
{
	public static Comparator<Partner> by( final String orderAttribute )
	{
		switch ( orderAttribute )
		{
		case "name":
			return byName( );
		case "-name":
			return byName( ).reversed( );
		default:
			return byId( );
		}
	}

	public static Comparator<Partner> byId( )
	{
		return Comparator.comparing( Partner::getId );
	}

	public static Comparator<Partner> byName( )
	{
		return Comparator.comparing( Partner::getNameLower );
	}

}