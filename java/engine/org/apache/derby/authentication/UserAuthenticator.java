/*

   Licensed Materials - Property of IBM
   Cloudscape - Package org.apache.derby.authentication
   (C) Copyright IBM Corp. 1998, 2004. All Rights Reserved.
   US Government Users Restricted Rights - Use, duplication or
   disclosure restricted by GSA ADP Schedule Contract with IBM Corp.

 */

package org.apache.derby.authentication;

import java.util.Properties;
import java.sql.SQLException;

/**
 * The UserAuthenticator interface provides operations to authenticate
 * a user's credentials in order to successfully connect to a database.
 * 
 * Any user authentication schemes could be implemented using this interface
 * and registered at start-up time.
 * <p>
 * If an application requires its own authentication scheme, then it can
 * implement this interface and register as the authentication scheme
 * that Cloudscape should call upon connection requests to the system.
   See the dcoumentation for the property <I>derby.authentication.provider</I>
 * <p>
 * A typical example would be to implement user authentication using
 * LDAP, Sun NIS+, or even Windows User Domain, using this interface.
 * <p>
 * <i>Note</i>: Additional connection attributes can be specified on the database
 * connection URL and/or Properties object on jdbc connection. Values
 * for these attributes can be retrieved at runtime by the (specialized)
 * authentication scheme to further help user authentication, if one needs
 * additional info other than user, password, and database name.
 *
 *
 */

public interface UserAuthenticator
{
	
	/**
	 * Authenticate a user's credentials.
	 *
	 * @param userName		The user's name for the connection request. May be null.
	 * @param userPassword	The user's password for the connection request. May be null.
	 * @param databaseName	The database that the user wants to connect to.
	 *						Will be null if this is system level authentication.
	 * @param info			A Properties object that contains additional
	 * connection information, that can help to authenticate the user. It
	 * has properties of the 'info' object passed as part of
	 * DriverManager.getConnection() call and any
	 * attributes set on the JDBC URL.

		@return	false if the connection request should be denied, true if the connection request should proceed.
		If false is returned the connection attempt will receive a SQLException with SQL State 08004.
	 *
	 * @exception java.sql.SQLException An exception processing the request, connection request will be denied.
		The SQL exception will be returned to the connection attempt.
	 */
	public boolean	authenticateUser(String userName,
								 String userPassword,
								 String databaseName,
								 Properties info
								)
		throws SQLException;
}
