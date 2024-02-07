package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.dao.MealsDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final int CALORIES_PER_DAY = 2000;

    private Dao<Meal, Integer> dao;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = new MealsDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalTime startTime = LocalTime.of(0, 0);
        LocalTime endTime = LocalTime.of(23, 59);

        log.debug("get meals list and convert it to mealsTo list");
        List<MealTo> mealsTo = MealsUtil.filteredByStreams(dao.findAll(), startTime, endTime, CALORIES_PER_DAY);
        request.setAttribute("mealsTo", mealsTo);
        log.debug("redirect to meals");
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}
