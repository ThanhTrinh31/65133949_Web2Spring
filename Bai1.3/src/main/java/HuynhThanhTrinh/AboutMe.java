package HuynhThanhTrinh;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/AboutMe")
public class AboutMe extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // chuyển sang file HTML
        request.getRequestDispatcher("/aboutme.html")
               .forward(request, response);
    }
}
