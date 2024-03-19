package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
    @RequestMapping("/meals")
    public class JspMealController extends AbstractMealController {
        public JspMealController(MealService service) {
            super(service);
        }

        @GetMapping
        public String getAllMeals(Model model, HttpServletRequest request) {
            LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
            LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
            LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
            LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
            model.addAttribute("meals", super.getBetween(startDate, startTime, endDate, endTime));
            return "meals";
        }

        @GetMapping(params = "create")
        public String getCreate(Model model, HttpServletRequest request, HttpServletResponse response) {
            createOrUpdateMeal(model, request, true);
            return "mealForm";
        }

        @GetMapping(params = "update")
        public String getUpdate(Model model, HttpServletRequest request) {
            createOrUpdateMeal(model, request, false);
            return "mealForm";
        }

        @GetMapping(params = "delete")
        public String getDelete(HttpServletRequest request) {
            super.delete(getId(request));
            return "redirect:/meals";
        }

    @PostMapping
    public String postUpdate(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (StringUtils.hasLength(request.getParameter("id"))) {
            super.update(meal, getId(request));
        } else {
            super.create(meal);
        }
        return "redirect:/meals";
    }

        private void createOrUpdateMeal(Model model, HttpServletRequest request, boolean isNew) {
            Meal meal;
            if (isNew) {
                model.addAttribute("meal", new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000));
                model.addAttribute("action", "create");
            } else {
                model.addAttribute("meal", super.get(getId(request)));
                model.addAttribute("action", "update");
            }
        }

        private int getId(HttpServletRequest request) {
            String paramId = Objects.requireNonNull(request.getParameter("id"));
            return Integer.parseInt(paramId);
        }
}
