package com.tugalsan.java.core.file.html.chart.server;

import module com.tugalsan.java.core.list;
import module com.tugalsan.java.core.time;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.tuple;
import module com.tugalsan.java.core.stream;
import module com.tugalsan.java.core.string;
import java.util.*;
import java.util.stream.*;

public class TS_FileHtmlChartUtils {

    private TS_FileHtmlChartUtils() {

    }

    final private static TS_Log d = TS_Log.of(TS_FileHtmlChartUtils.class);

    private static void addLine(List<String> strBuffer, String s) {
        strBuffer.add(TGS_StringUtils.cmn().concat(s, "\n"));
    }

    public static void addHTML_datePicker(List<String> dest, TGS_Time dateFrom, TGS_Time dateTo, List<TGS_Tuple2<String, String>> hidden) {
        addLine(dest, TGS_StringUtils.cmn().concat(
                "<form method=\"get\" action=\"\">",
                TGS_StringUtils.cmn().concat(TGS_StreamUtils.toLst(
                        hidden.stream().map(h -> TGS_StringUtils.cmn().concat(
                        "<input id=\"", h.value0, "\" name=\"", h.value0, "\" type=\"hidden\" value=\"", h.value1, "\">"
                )))),
                "From:<input type=\"date\" name=\"dateFrom\" value=\"", dateFrom.toString_YYYY_MM_DD(), "\"> ",
                "To:<input type=\"date\" name=\"dateTo\" value=\"", dateTo.toString_YYYY_MM_DD(), "\"> ",
                "<input type=\"submit\" value=\"Submit\"></form>"
        ));
    }

    public static class HTMLChart {

        public List<String> seriNames;
        public List<HTMLChartData> data;
        public String xTitle, yTitle;

        public HTMLChart(String xTitle, String yTitle) {
            this.xTitle = xTitle;
            this.yTitle = yTitle;
            data = TGS_ListUtils.of();
            seriNames = TGS_ListUtils.of();
        }
    }

    public static class HTMLChartData {

        public String xLabel;
        public double[] yValues;

        public HTMLChartData(String xLabel, double[] yValues) {
            this.xLabel = xLabel;
            this.yValues = yValues;
        }
    }

    public static void addHTML_chart(List<String> strBuffer, List<HTMLChart> charts, CharSequence googleChartsJs) {
        addLine(strBuffer, "    <script src=\"" + googleChartsJs + "\"></script>");
        addLine(strBuffer, "    <script>");
        addLine(strBuffer, "        google.load('visualization', '1.0', {'packages': ['corechart', 'line']});");
        addLine(strBuffer, "        google.setOnLoadCallback(drawChart);");
        addLine(strBuffer, "        function drawLineChart(divName, data, options) {");
        addLine(strBuffer, "            var chart = new google.visualization.LineChart(document.getElementById(divName));");
        addLine(strBuffer, "            chart.draw(data, options);");
        addLine(strBuffer, "        }");
        addLine(strBuffer, "        function drawHistogram(divName, data, options) {");
        addLine(strBuffer, "            var chart = new google.visualization.Histogram(document.getElementById(divName));");
        addLine(strBuffer, "            chart.draw(data, options);");
        addLine(strBuffer, "        }");
        IntStream.range(0, charts.size()).forEachOrdered(i -> {
            List<String> destI = TGS_ListUtils.of();
            var c = charts.get(i);
            addLine(destI, TGS_StringUtils.cmn().concat("        function getDataLineChart", String.valueOf(i), "(){"));
            addLine(destI, "            var data = new google.visualization.DataTable();");
            addLine(destI, "            data.addColumn('string', 'X');");
            addLine(destI, TGS_StringUtils.cmn().concat("            data.addColumn('number', '", c.seriNames.get(0), "');"));
            addLine(destI, TGS_StringUtils.cmn().concat("            data.addColumn('number', '", c.seriNames.get(1), "');"));
            addLine(destI, "            data.addRows([");
            IntStream.range(0, c.data.size()).forEachOrdered(di -> {
                var dataI = c.data.get(di);
                Double nem = dataI.yValues[0];//DO NOT CHANGE IT TO VAR, java bug!!! 
                Double sic = dataI.yValues[1];//DO NOT CHANGE IT TO VAR, java bug!!!
                d.ci("addHTML_chart", i, di, nem, sic);
                nem = nem == 0f ? null : nem;
                sic = sic == 0f ? null : sic;
                addLine(destI, TGS_StringUtils.cmn().concat("                ['", dataI.xLabel, "', ", String.valueOf(nem), ", ", String.valueOf(sic), "]", (di != c.data.size() - 1 ? "," : "")));
            });
            addLine(destI, "            ]);");
            addLine(destI, "            return data;");
            addLine(destI, "        }");
            addLine(destI, TGS_StringUtils.cmn().concat("      function getOptionsLineChart", String.valueOf(i), "() {"));
            addLine(destI, "        var options = {");
            addLine(destI, "          backgroundColor: '#AAAAAA',");
            addLine(destI, "          chartArea: {width:500,height:120,left:20,top:20},");
            addLine(destI, "          chartArea: {width:'82%', height: '60%', top: '9%', left: '5%', right: '20%', bottom: '%8'},");
            addLine(destI, "          interpolateNulls: true,");
            addLine(destI, "          hAxis: {");
            addLine(destI, TGS_StringUtils.cmn().concat("            title: '", c.xTitle, "',"));
            addLine(destI, "            slantedText: true,");
            addLine(destI, "            textStyle: {");
            addLine(destI, "               fontSize: 10");
            addLine(destI, "            }");
            addLine(destI, "          },");
            addLine(destI, "          vAxis: {");
            addLine(destI, TGS_StringUtils.cmn().concat("            title: '", c.yTitle, "',"));
            addLine(destI, "            slantedText: true,");
            addLine(destI, "            textStyle: {");
            addLine(destI, "               fontSize: 10");
            addLine(destI, "            },");
            addLine(destI, "            minValue: 0,");
            addLine(destI, "            maxValue: 100,");
            addLine(destI, "            gridlines: {count: 10},");
            addLine(destI, "          },");
            addLine(destI, "          colors: ['#AB0D06', '#007329'],");
            addLine(destI, "          trendlines: {");
            addLine(destI, "            0: {type: 'exponential', color: '#111', opacity: 1},");
            addLine(destI, "            1: {type: 'exponential', color: '#333', opacity: 1}");//linear .3
            addLine(destI, "          }");
            addLine(destI, "        };");
            addLine(destI, "        return options;");
            addLine(destI, "      }");
            addLine(destI, "      function getOptionsHistogram" + i + "() {");
            addLine(destI, "        var options = {");
            addLine(destI, "          backgroundColor: '#AAAAAA',");
            addLine(destI, TGS_StringUtils.cmn().concat("          title: '", c.yTitle, "',"));
            addLine(destI, "          colors: ['#AB0D06', '#007329'],");
            addLine(destI, "          hAxis: {");
            addLine(destI, "            ticks: [0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100]");
            addLine(destI, "          },");
            addLine(destI, "          bar: { gap: 0 },");
            addLine(destI, "          histogram: {");
            addLine(destI, "            bucketSize: 0.02,");
            addLine(destI, "            maxNumBuckets: 200,");
            addLine(destI, "            minValue: -1,");
            addLine(destI, "            maxValue: 1");
            addLine(destI, "          }");
            addLine(destI, "        };");
            addLine(destI, "        return options;");
            addLine(destI, "      }");
            strBuffer.addAll(destI);
        });
        addLine(strBuffer, "        function drawChart() {");
        IntStream.range(0, charts.size()).forEachOrdered(i -> {
            List<String> destI = TGS_ListUtils.of();
            addLine(destI, TGS_StringUtils.cmn().concat("            drawLineChart('divLineChart", String.valueOf(i), "', getDataLineChart", String.valueOf(i), "(), getOptionsLineChart", String.valueOf(i), "());"));
            addLine(destI, TGS_StringUtils.cmn().concat("            drawHistogram('divHistogram", String.valueOf(i), "', getDataLineChart", String.valueOf(i), "(), getOptionsHistogram", String.valueOf(i), "());"));
            strBuffer.addAll(destI);
        });
        addLine(strBuffer, "        }");
        addLine(strBuffer, "    </script>");
        addLine(strBuffer, "<table>");
        IntStream.range(0, charts.size()).forEachOrdered(i -> {
            List<String> destI = TGS_ListUtils.of();
            addLine(destI, "      <tr>");
            addLine(destI, TGS_StringUtils.cmn().concat("          <td id=\"divLineChart", String.valueOf(i), "\" style=\"width: 900px; height: 250px;\"></td>"));
            addLine(destI, "     </tr>");
            addLine(destI, "      <tr>");
            addLine(destI, TGS_StringUtils.cmn().concat("          <td id=\"divHistogram", String.valueOf(i), "\" style=\"width: 900px; height: 250px;\"></td>"));
            addLine(destI, "     </tr>");
            strBuffer.addAll(destI);
        });
        addLine(strBuffer, "</table>");
    }
}
