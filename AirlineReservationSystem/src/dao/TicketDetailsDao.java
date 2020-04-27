package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.TicketDetailsModel;


public class TicketDetailsDao extends DBConnect{
	// Declare DB objects
			DBConnect connection = new DBConnect();
			
			// Fetch the data from table
			public ArrayList<TicketDetailsModel> getCustomer(String txtUsername)
			{
				String Sql = "Select LNAME,FNAME,EMAIL,PHONE,FROMDEST,TODEST,TRAVELDATE,TRAVELTIME,CLASS,STATUS,BOOKINGID from ars_ticketdetails where UNAME = " 
						+ "'" + txtUsername +"'" ;	
				ArrayList<TicketDetailsModel> Ticket = new ArrayList<TicketDetailsModel>();
				ResultSet rs = null;
				
				try
				{
					Statement stmt = connection.getConnection().createStatement();
					
					System.out.println(Sql);
					
					rs = stmt.executeQuery(Sql);						
					
					if(rs.next())
					{						
						TicketDetailsModel t1 = new TicketDetailsModel();
						// set result set to the text fields
						t1.setlblLname(rs.getString(1));
						t1.setlblFname(rs.getString(2));
						t1.setlblEmail(rs.getString(3));
						t1.setlblPhone(rs.getLong(4));
						t1.setlblFrom(rs.getString(5));
						t1.setlblTo(rs.getString(6));
						t1.setlblDate(rs.getString(7));
						t1.setlblTime(rs.getString(8));
						t1.setlblClass(rs.getString(9));
						t1.setlblStatus(rs.getString(10));
						t1.setlblBookingId(rs.getInt(11));
						Ticket.add(t1);
						System.out.println("Sucessfully fetched ticket details from database");
					}
					return Ticket;		
				}
				catch (SQLException e) 
				{
					System.out.println("Error while fetching the user Information: " + e.getMessage());
					return null;
				}
			}
			
	

}
