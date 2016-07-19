package com.wfanalist.mvc;

import com.wfanalist.mvc.dao.WFanalistDAO;
import com.wfanalist.mvc.jspmodel.ChartsDataList;
import com.wfanalist.mvc.jspmodel.TableStatisticDataList;
import com.wfanalist.mvc.model.*;
import com.wfanalist.mvc.service.ScheduledTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MainController extends HttpServlet {
	//@RequestMapping("/")

	@Autowired
	WFanalistDAO wfanalistDAO;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ScheduledTask st;

	@Autowired
	private ChartsDataList chartsList;

	@Autowired
	private TableStatisticDataList tableList;

	@Autowired
	private ApplicationContext appContext;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printIndex(ModelMap model, Locale locale) {

		//Нужно будет убрать полностью temperature. А оставить только today и today_l, тк и по текущим и по истории отображаются только они

		model.addAttribute("locale", messageSource.getMessage("locale", null, locale));

		Cities cityKiev = wfanalistDAO.findCityById(1);
		List<Current_indicators> current_indicators = wfanalistDAO.getcurrentIndicatorsListByCity(cityKiev);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		model.addAttribute("curDate", dateFormat.format(new Date()));

		model.addAttribute("curInd", current_indicators);

		chartsList.fillListOfCharts(wfanalistDAO.findCityByName("Kyiv"));
		model.addAttribute("chartsList", chartsList);

		//testFSource();

		return "index";

	}

	@RequestMapping(value = "/statistic", method = RequestMethod.GET)
	public String printStatistic(ModelMap model, Locale locale) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		model.addAttribute("curDate", dateFormat.format(new Date()));

		tableList.fillListOfTables(wfanalistDAO.findCityByName("Kyiv"));
		model.addAttribute("tableList", tableList);

		return "statistic";
	}

	@RequestMapping(value = "/notready", method = RequestMethod.GET)
	public String printNotReady(ModelMap model, Locale locale) {

		return "notready";
	}

	private void testFSource() {

		//Sinoptik sr1 = (Sinoptik) appContext.getBean("Sinoptik");
		//sr1.parseReference("https://sinoptik.ua/%D0%BF%D0%BE%D0%B3%D0%BE%D0%B4%D0%B0-%D0%BA%D0%B8%D0%B5%D0%B2");

		//Theweathernetwork sr2 = (Theweathernetwork) appContext.getBean("Theweathernetwork");
		//sr2.parseReference("http://www.theweathernetwork.com/ua/weather/kyiv-kiev-city/kiev?wx_auto_reload=forecasts:%20city%20page:%20auto%20refresh");

		//Accuweather sr3 = (Accuweather) appContext.getBean("Accuweather");
		//sr3.parseReference("http://www.accuweather.com/en/ua/kyiv/324505/daily-weather-forecast/324505?lang=en-us");

	}

	public void init() throws ServletException
	{
		//We need to start our job once a server starts

		ScheduledTask sTask = (ScheduledTask) appContext.getBean("ScheduledTask");
		sTask.run();
	}

}