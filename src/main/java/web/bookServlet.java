package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import user.user;
import userDao.userDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/book")
public class bookServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String event = req.getParameter("event");
        if (event.equals("viewBook")) {
            String name = req.getParameter("name");
            String author = req.getParameter("author");
            int price = Integer.parseInt(req.getParameter("price"));
            String publisher = req.getParameter("publisher");
            String isbn = req.getParameter("isbn");
            user u = new user();
            u.setName(name);
            u.setAuthor(author);
            u.setPrice(price);
            u.setPublisher(publisher);
            u.setIsbn(isbn);
            int result = userDao.saveDetails(u);
            if (result == 1) {
                out.print("<!DOCTYPE html>");
                out.print("<html>");
                out.print("<head>");
                out.print("<title>Book Addition Status</title>");

                out.print("<style>");
                out.print("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }");
                out.print(".container { width: 50%; margin: auto; padding: 20px; background-color: #fff; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); text-align: center; }");
                out.print("h2 { color: #4CAF50; }");
                out.print(".btn { margin-top: 20px; background-color: #007bff; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none; display: inline-block; }");
                out.print(".btn:hover { background-color: #0056b3; }");
                out.print("</style>");

                out.print("</head>");
                out.print("<body>");

                out.print("<div class='container'>");
                out.print("<h2>Book Successfully Added!</h2>");
                out.print("<a href='index.jsp' class='btn'>Add Another Book</a>");
                out.print("</div>");

                out.print("</body>");
                out.print("</html>");
            } else {
                resp.setContentType("text/html");
                out.print("<!DOCTYPE html>");
                out.print("<html>");
                out.print("<head>");
                out.print("<title>Book Addition Status</title>");

                out.print("<style>");
                out.print("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }");
                out.print(".container { width: 50%; margin: auto; padding: 20px; background-color: #fff; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); text-align: center; }");
                out.print("h2 { color: #f44336; }");
                out.print(".btn { margin-top: 20px; background-color: #007bff; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none; display: inline-block; }");
                out.print(".btn:hover { background-color: #0056b3; }");
                out.print("</style>");

                out.print("</head>");
                out.print("<body>");

                out.print("<div class='container'>");
                out.print("<h2>Failed to Add Book!</h2>");
                out.print("<a href='addBookForm.jsp' class='btn'>Try Again</a>");
                out.print("<a href='viewBooksServlet' class='btn'>View Books</a>");
                out.print("</div>");

                out.print("</body>");
                out.print("</html>");
            }
        } else if (event.equals("addbook")) {
            resp.setContentType("text/html");
            userDao ed = new userDao();
            List<user> listEmp = ed.selectUser();

            out.print("<html>");
            out.print("<head>");
            out.print("<title>Submitted Book Details</title>");

            out.print("<style>");
            out.print("table { width: 100%; border-collapse: collapse; margin: 20px 0; font-family: Arial, sans-serif; }");
            out.print("th, td { padding: 12px; text-align: left; border: 1px solid #ddd; }");
            out.print("th { background-color: #4CAF50; color: white; }");
            out.print("tr:nth-child(even) { background-color: #f2f2f2; }");
            out.print("tr:hover { background-color: #ddd; }");
            out.print("a { text-decoration: none; color: #4CAF50; }");
            out.print("a:hover { color: #45a049; }");
            out.print(".btn-back {");
            out.print("display: inline-block;");
            out.print("margin-top: 20px;");
            out.print("padding: 10px 15px;");
            out.print("background-color: #007bff;");
            out.print("color: white;");
            out.print("text-decoration: none;");
            out.print("border-radius: 5px;");
            out.print("text-align: center;");
            out.print("}");
            out.print(".btn-back:hover {");
            out.print("background-color: #0056b3;");
            out.print("}");
            out.print("</style>");

            out.print("</head>");
            out.print("<body>");

            out.print("<table>");
            out.print("<tr>");
            out.print("<th>ID</th>");
            out.print("<th>Book Name</th>");
            out.print("<th>Author Name</th>");
            out.print("<th>Price</th>");
            out.print("<th>Publisher</th>");
            out.print("<th>ISBN</th>");
            out.print("<th>Edit</th>");
            out.print("<th>Delete</th>");
            out.print("</tr>");

            for (user book : listEmp) {
                out.print("<tr>");
                out.print("<td>" + book.getId() + "</td>");
                out.print("<td>" + book.getName() + "</td>");
                out.print("<td>" + book.getAuthor() + "</td>");
                out.print("<td>" + book.getPrice() + "</td>");
                out.print("<td>" + book.getPublisher() + "</td>");
                out.print("<td>" + book.getIsbn() + "</td>");
                out.print("<td><a href='book?event=edituser&id=" + book.getId() + "'>Edit</a></td>");
                out.print("<td><a href='book?event=deletebook&id=" + book.getId() + "'>Delete</a></td>");
                out.print("</tr>");
            }

            out.print("</table>");

            out.print("<a href='index.jsp' class='btn-back'>Back</a>");

            out.print("</body>");
            out.print("</html>");
        } else if (event.equals("deletebook")) {

            int id = Integer.parseInt(req.getParameter("id"));
            resp.setContentType("text/html");

            int result = userDao.deleteUser(id);
            if (result != 0) {

                out.print("<!DOCTYPE html>");
                out.print("<html>");
                out.print("<head>");
                out.print("<title>Deletion Status</title>");

                out.print("<style>");
                out.print("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }");
                out.print(".container { width: 50%; margin: auto; padding: 20px; background-color: #fff; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); text-align: center; }");
                out.print("h2 { color: #4CAF50; }");
                out.print(".btn-back { margin-top: 20px; background-color: #007bff; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none; display: inline-block; }");
                out.print(".btn-back:hover { background-color: #0056b3; }");
                out.print("</style>");

                out.print("</head>");
                out.print("<body>");

                out.print("<div class='container'>");
                out.print("<h2>Successfully Deleted!</h2>");
                // out.print("<a href='viewServlet' class='btn-back'>Back</a>");
                out.print("</div>");

                out.print("</body>");
                out.print("</html>");
            } else {
                resp.setContentType("text/html");
                out.print("<!DOCTYPE html>");
                out.print("<html>");
                out.print("<head>");
                out.print("<title>Deletion Status</title>");

                out.print("<style>");
                out.print("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }");
                out.print(".container { width: 50%; margin: auto; padding: 20px; background-color: #fff; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); text-align: center; }");
                out.print("h2 { color: #f44336; }");
                out.print(".btn-back { margin-top: 20px; background-color: #007bff; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none; display: inline-block; }");
                out.print(".btn-back:hover { background-color: #0056b3; }");
                out.print("</style>");

                out.print("</head>");
                out.print("<body>");

                out.print("<div class='container'>");
                out.print("<h2>Deletion Failed!</h2>");
                out.print("</div>");

                out.print("</body>");
                out.print("</html>");
            }
        } else if (event.equals("edituser")) {

            int id = Integer.parseInt(req.getParameter("id"));
            user book = new user();
            book = userDao.getUserbyid(id);

            resp.setContentType("text/html");
            out.print("<!DOCTYPE html>");
            out.print("<html>");
            out.print("<head>");
            out.print("<title>Update Book</title>");

            out.print("<style>");
            out.print("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }");
            out.print(".container { width: 50%; margin: auto; padding: 20px; background-color: #fff; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
            out.print("h2 { text-align: center; color: #333; }");
            out.print("label { display: block; margin-top: 10px; color: #555; }");
            out.print("input[type='text'], input[type='number'] { width: 100%; padding: 8px; margin-top: 5px; border: 1px solid #ccc; border-radius: 4px; }");
            out.print("input[type='submit'] { margin-top: 20px; background-color: #4CAF50; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; }");
            out.print("input[type='submit']:hover { background-color: #45a049; }");
            out.print(".btn-back { margin-top: 20px; background-color: #007bff; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none; display: inline-block; }");
            out.print(".btn-back:hover { background-color: #0056b3; }");
            out.print("</style>");

            out.print("</head>");
            out.print("<body>");
            out.print("<div class='container'>");
            out.print("<h2>Update Book Details</h2>");

            out.print("<form action='book' method='get'>");
            out.print("<input type='hidden' name='id' value='" + book.getId() + "' />");

            out.print("<label for='name'>Book Name:</label>");
            out.print("<input type='text' name='name' value='" + book.getName() + "' required />");

            out.print("<label for='author'>Author Name:</label>");
            out.print("<input type='text' name='author' value='" + book.getAuthor() + "' required />");

            out.print("<label for='price'>Price:</label>");
            out.print("<input type='number' step='0.01' name='price' value='" + book.getPrice() + "' required />");

            out.print("<label for='publisher'>Publisher:</label>");
            out.print("<input type='text' name='publisher' value='" + book.getPublisher() + "' required />");

            out.print("<label for='isbn'>ISBN:</label>");
            out.print("<input type='text' name='isbn' value='" + book.getIsbn() + "' required />");

            out.println("<tr><td colspan='2' style='text-align: center;'><input type='submit' value='Update'/></td></tr>");
            out.println("<tr><input type='hidden' name='event' value='Update'/></tr>");



            out.print("</body>");
            out.print("</html>");
        } else if (event.equals("Update")) {

            String name = req.getParameter("name");
            String author = req.getParameter("author");
            int price = Integer.parseInt(req.getParameter("price"));
            String publisher = req.getParameter("publisher");
            String isbn = req.getParameter("isbn");
            int id = Integer.parseInt(req.getParameter("id"));
            user u = new user();
            u.setName(name);
            u.setAuthor(author);
            u.setPrice(price);
            u.setPublisher(publisher);
            u.setIsbn(isbn);
            u.setId(id);
            int result =userDao.updateUser(u);
            if (result == 1) {
                resp.setContentType("text/html");
                out.print("<!DOCTYPE html>");
                out.print("<html>");
                out.print("<head>");
                out.print("<title>Update Status</title>");

                out.print("<style>");
                out.print("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }");
                out.print(".container { width: 50%; margin: auto; padding: 20px; background-color: #fff; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); text-align: center; }");
                out.print("h2 { color: #4CAF50; }");
                out.print(".btn-back { margin-top: 20px; background-color: #007bff; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none; display: inline-block; }");
                out.print(".btn-back:hover { background-color: #0056b3; }");
                out.print("</style>");

                out.print("</head>");
                out.print("<body>");

                out.print("<div class='container'>");
                out.print("<h2>Successfully Updated!</h2>");
                out.print("<a href='book' class='btn-back'>Back</a>");
                out.print("</div>");

                out.print("</body>");
                out.print("</html>");
            } else {

                out.print("<!DOCTYPE html>");
                out.print("<html>");
                out.print("<head>");
                out.print("<title>Update Status</title>");

                out.print("<style>");
                out.print("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }");
                out.print(".container { width: 50%; margin: auto; padding: 20px; background-color: #fff; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); text-align: center; }");
                out.print("h2 { color: #f44336; }");
                out.print(".btn-back { margin-top: 20px; background-color: #007bff; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none; display: inline-block; }");
                out.print(".btn-back:hover { background-color: #0056b3; }");
                out.print("</style>");

                out.print("</head>");
                out.print("<body>");

                out.print("<div class='container'>");
                out.print("<h2>Update Failed!</h2>");
                out.print("<a href='viewServlet' class='btn-back'>Back</a>");
                out.print("</div>");

                out.print("</body>");
                out.print("</html>");
            }


        }
    }
}