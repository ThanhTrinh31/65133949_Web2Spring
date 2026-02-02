package HuynhThanhTrinh;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/BMI")
public class BMI extends HttpServlet {

    // GET → form
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/BMI_form.html")
               .forward(request, response);
    }

    // POST → tính + forward kết quả
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        double h = Double.parseDouble(request.getParameter("height"));
        double w = Double.parseDouble(request.getParameter("weight"));

        double bmi = w / (h * h);

        String rank;
        if (bmi < 18.5) rank = "Gầy";
        else if (bmi < 25) rank = "Bình thường";
        else if (bmi < 30) rank = "Thừa cân";
        else rank = "Béo";

        request.setAttribute("bmi", String.format("%.2f", bmi));
        request.setAttribute("rank", rank);

        request.getRequestDispatcher("/BMI_result.jsp")
               .forward(request, response);
    }
}
