package com.github.yhs0092.javachassis.demo.customservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.servicecomb.common.rest.codec.AbstractRestObjectMapper;
import org.apache.servicecomb.common.rest.codec.RestObjectMapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@WebServlet(name = "testCustomServlet", urlPatterns = {"/testCustomServlet", "/testCustomServlet/*"})
public class MyTestServlet extends HttpServlet {

  private static final long serialVersionUID = -181025327532345174L;

  private static final Logger LOGGER = LoggerFactory.getLogger(MyTestServlet.class);

  @Autowired
  private PersonManager personManager;

  public MyTestServlet() {
    LOGGER.info("MyTestServlet constructed!");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    LOGGER.info("doGet is called, path = [{}]", req.getPathInfo());
    if (isInvalidPath(req)) {
      responseUnknownPath(req, resp);
      return;
    }

    String name = req.getParameter("name");
    Person person = personManager.findPerson(name);
    if (null == person) {
      responsePersonNotFound(resp, name);
      return;
    }
    response(resp, 200, person);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    LOGGER.info("doPut is called, path = [{}]", req.getPathInfo());
    if (isInvalidPath(req)) {
      responseUnknownPath(req, resp);
      return;
    }

    Person person;
    try {
      person = getObjectMapper().readValue(req.getInputStream(), Person.class);
    } catch (IOException e) {
      response(resp, 400, new CommonResponse("failed to parse body: " + e.getMessage()));
      return;
    }

    personManager.addPerson(person);
    response(resp, 200, new CommonResponse("OK"));
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    LOGGER.info("doDelete is called, path = [{}]", req.getPathInfo());
    if (isInvalidPath(req)) {
      responseUnknownPath(req, resp);
      return;
    }

    String name = req.getParameter("name");
    Person person = personManager.deletePerson(name);
    if (null == person) {
      responsePersonNotFound(resp, name);
      return;
    }
    response(resp, 200, person);
  }

  private void response(HttpServletResponse resp, int statusCode, Object respBody) throws IOException {
    resp.setStatus(statusCode);
    resp.setContentType("application/json");
    PrintWriter writer = resp.getWriter();
    writer.print(getObjectMapper().writeValueAsString(respBody));
  }

  private boolean isInvalidPath(HttpServletRequest req) {
    return !"/person".equals(req.getPathInfo());
  }

  private void responsePersonNotFound(HttpServletResponse resp, String name) throws IOException {
    response404(resp, "there is no person named " + name);
  }

  private void responseUnknownPath(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    response404(resp, "unknown path: " + req.getPathInfo());
  }

  private void response404(HttpServletResponse resp, String msg) throws IOException {
    response(resp, 404, new CommonResponse(msg));
  }

  private AbstractRestObjectMapper getObjectMapper() {
    return RestObjectMapperFactory.getRestObjectMapper();
  }
}
