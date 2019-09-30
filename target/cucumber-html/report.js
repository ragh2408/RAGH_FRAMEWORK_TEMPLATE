$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("WebTest.feature");
formatter.feature({
  "comments": [
    {
      "line": 1,
      "value": "#Author: Ragh.yadav2408@gmail.com"
    }
  ],
  "line": 2,
  "name": "Title of your feature",
  "description": "",
  "id": "title-of-your-feature",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 4,
  "name": "Checking the Templete for WEB PORTAL",
  "description": "",
  "id": "title-of-your-feature;checking-the-templete-for-web-portal",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "Launch the browser",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "Enter th URL Google.Com",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "Enter the usename",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "Enter the Password",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "Click on the Signon Botton",
  "keyword": "And "
});
formatter.match({
  "location": "TC_01.launch_the_browser()"
});
formatter.result({
  "duration": 435411700,
  "status": "passed"
});
formatter.match({
  "location": "TC_01.enter_th_URL_Google_Com()"
});
formatter.result({
  "duration": 308500,
  "status": "passed"
});
formatter.match({
  "location": "TC_01.enter_the_usename()"
});
formatter.result({
  "duration": 586400,
  "status": "passed"
});
formatter.match({
  "location": "TC_01.enter_the_Password()"
});
formatter.result({
  "duration": 5001200,
  "error_message": "junit.framework.ComparisonFailure: expected:\u003c[a]\u003e but was:\u003c[bb]\u003e\r\n\tat junit.framework.Assert.assertEquals(Assert.java:100)\r\n\tat junit.framework.Assert.assertEquals(Assert.java:107)\r\n\tat com.optum.ndb.stepdefinitions.TC_01.enter_the_Password(TC_01.java:28)\r\n\tat ✽.And Enter the Password(WebTest.feature:8)\r\n",
  "status": "failed"
});
formatter.match({
  "location": "TC_01.click_on_the_Signon_Botton()"
});
formatter.result({
  "status": "skipped"
});
});