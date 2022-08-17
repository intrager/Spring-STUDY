<%--
  Created by IntelliJ IDEA.
  User: intra
  Date: 2022-08-17
  Time: 오전 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <h1>Ajax Home</h1>

    <form>
        bno : <input type="text" name="bno" value="" id="bno" /><br>
        title : <input type="text" name="title" value="" id="title" /><br>
        contents : <input type="text" name="contents" id="contents" /><br>
        writer : <input type="text" name="writer" value="" id="writer" /><br>
    </form>

    <div>
        <button id="getBtn">READ</button>
        <button id="getJsonBtn">READ(JSON)</button>
        <button id="getXmlBtn">READ(XML)</button>
    </div>

    <div>
        <button id="putBtn">MODIFY(post)</button>
        <button id="putJsonBtn">MODIFY(put json)</button>
        <button id="putXmlBtn">MODIFY(put xml)</button>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"> </script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#putBtn").on("click", function() {
                var bno = $("#bno");
                var title = $("#title");
                var contents = $("#contents");
                var writer = $("#writer");

                var bnoVal = bno.val();
                var titleVal = title.val();
                var contentsVal = contents.val();
                var writerVal = writer.val();

                var boardObject = {
                    bno : bnoVal,
                    title: titleVal,
                    contents : contentsVal,
                    writer : writerVal
                };

                $.ajax({
                    type : "put",
                    url : "/board/" + bnoVal,
                    data : JSON.stringify(boardObject),
                    contentType : "application/json; charset=UTF-8",
                    success: function (result) {
                        console.log("result" + result);

                        alert(result);
                    }
                });
            });

            $("#putHeaderBtn").on("click", function () {
                var bno = $("#bno");
                var title = $("#title");
                var contents = $("#contents");
                var writer = $("#writer");

                var bnoVal = bno.val();
                var titleVal = title.val();
                var contentsVal = contents.val();
                var writerVal = writer.val();

                var boardObject = {
                    bno : bnoVal,
                    title : titleVal,
                    contents : contentsVal,
                    writer : writerVal
                };

                $.ajax({
                    type : "put",
                    url : "/board/" + bnoVal,
                    headers : {
                        "X-HTTP-Method-Override" : "put"
                    },
                    data : JSON.stringify(boardObject),
                    contentType : "application/json; charset=UTF-8",
                    success : function(result) {
                        console.log("result : " + result);

                        alert(result);
                    }
                });
            });

            $("#postBtn").on("click", function () {
                var bno = $("#bno");
                var title = $("#title");
                var contents = $("#contents");
                var writer = $("#writer");

                var bnoVal = bno.val();
                var titleVal = title.val();
                var contentsVal = contents.val();
                var writerVal = writer.val();

                var boardObject = {
                    bno : bnoVal,
                    title : titleVal,
                    contents : contentsVal,
                    writer : writerVal
                };

                $.ajax({
                    type : "post",
                    url : "/board/" + bnoVal,
                    data : JSON.stringify(boardObject),
                    contentType : "application/json; charset=UTF-8",
                    success : function (result)  {
                        console.log("result : " + result);
                        alert(result);
                    }
                });
            });

            $("#putJsonBtn").on("click", function () {
                var bno = $("#bno");
                var title = $("#title");
                var contents = $("#contents");
                var writer = $("#writer");

                var bnoVal = bno.val();
                var titleVal = title.val();
                var contentsVal = contents.val();
                var writerVal = writer.val();

                var boardObject = {
                    bno : bnoVal,
                    title : titleVal,
                    contents : contentsVal,
                    writer : writerVal
                };

                $.ajax({
                    type : "put",
                    url : "/board/" + bnoVal,
                    data : JSON.stringify(boardObject),
                    contentType : "application/json; charset=UTF-8",
                    success : function (result) {
                        console.log("result" + result);
                        alert(result);
                    }
                });
            });

            $("#putXmlBtn").on("click", function () {
                var bno = $("#bno");
                var title = $("#title");
                var contents = $("#contents");
                var writer = $("#writer");

                var bnoVal = bno.val();
                var titleVal = title.val();
                var contentsVal = contents.val();
                var writerVal = writer.val();

                var xmlData = "<Board>";
                xmlData += "<bno>0</bno>";
                xmlData += "<title>title1</title>";
                xmlData += "<contents>contents1</contents>";
                xmlData += "<writer>writer1</writer>";
                xmlData += "</Board>";

                $.ajax({
                    type : "put",
                    url : "/board/" + bnoVal,
                    data : xmlData,
                    contentType : "application/xml; charset=UTF-8",
                    success : function (result) {
                        console.log("result : " + result);
                        alert(result);
                    }
                });
            });

            $("#getBtn").on("click", function () {
                var bno = $("#bno");
                var bnoVal = bno.val();

                console.log(bnoVal);
                $.get("/board/" + bnoVal, function (data) {
                    console.log(data);
                    alert(JSON.stringify(data));
                });
            });

            $("#getJsonBtn").on("click", function () {
                var bno = $("#bno");
                var bnoVal = bno.val();

                console.log(bnoVal);

                $.ajax({
                    type : "get",
                    url : "/board/" + bnoVal,
                    headers: {
                        "Accept" : "application/json"
                    },
                    success : function (result) {
                        console.log("result" + result);
                        alert(JSON.stringify(result));
                    }
                });
            });

            $("#getXmlBtn").on("click", function () {
                var bno = $("#bno");
                var bnoVal = bno.val();

                console.log(bnoVal);

                $.ajax({
                    type : "get",
                    url : "/board/" + bnoVal,
                    headers : {
                        "Accept" : "application/xml"
                    },
                    success : function (result) {
                        console.log("result" + result);
                        alert(xmlToString(result));
                    }
                });
            });
        });

        function xmlToString(xmlData) {
            var xmlString;

            if(window.ActiveXObject) {
                xmlString = xmlData.xml;
            } else {
                xmlString = (new XMLSerializer()).serializeToString(xmlData);
            }
            return xmlString;
        }
    </script>
</body>
</html>
