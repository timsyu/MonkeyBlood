<!doctype html>
<html>
<head>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="generator" content="Jekyll v3.8.5">
    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/cover/">
    
    
<meta charset="utf-8">

  <title>至尊血壓記錄寶</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>

.jumbotron {
        background: url("https://png.pngtree.com/thumb_back/fh260/background/20190223/ourmid/pngtree-medical-doctor-science-background-backgroundbackgrounddoctorsmedicalmedicinehealth-image_68227.jpg") no-repeat center center fixed;
        background-size: 100% auto;
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;
    }
    .col-md-6 {
        padding-right: 15px;
        padding-left: 15px;
    }
    .row{
        margin-bottom: 10px;
    }
    @media screen and (max-width: 768px) {
        div.col-md-6 {
            width: 100%;
        }
    }
</style>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script>
$(document).ready(function() {
    $('#newBPButton').click(function(e) {
        $("#userDate").prop('required',true);
        $("#userSystolic").prop('required',true);
        $("#userDiastolic").prop('required',true);
        $("#userPulse").prop('required',true);
        var userID = "${githubUserID}";
        var userName = "${githubUserName}";
        var systolic = $('#userSystolic').val();
        var diastolic = $('#userDiastolic').val();
        var pulse = $('#userPulse').val();
        var date = $('#userDate').val();
        console.log(userID);
        console.log(userName);
        console.log(systolic);
        console.log(diastolic);
        console.log(pulse);
        console.log(date);
        var cusData = new FormData(); //建構new FormData()
        cusData.append("githubID", userID);
        cusData.append("githubName", userName);
        cusData.append("systolic", systolic);
        cusData.append("diastolic", diastolic);
        cusData.append("pulse", pulse);
        cusData.append("date", date);
        console.log(cusData);
        $.ajax({
            url: "/BloodPressure/add",
            cache: false,
            contentType: false,
            processData: false,
            data: cusData, //data只能指定單一物件
            type: 'Post',
            success: function(data) {
                alert("新增成功！");
                window.location="/redirectToGithub";
            },
            error: function(data) {
                alert("失敗");
            }

        });
    });
});
function deleteBP(id, date) {
    console.log(id);
    console.log(date);
    var answer = confirm("確定要刪除這個記錄嗎？");
    if (answer) {
        var data1234 = new FormData();
        var userid = id;
        var userDate = date;
        console.log(id);
        console.log(date);
        data1234.append("githubID", userid);
        data1234.append("date", userDate);
        console.log(data1234);
        $.ajax({
            url: "/BloodPressure/delete",
            cache: false,
            contentType: false,
            processData: false,
            data: data1234,
            type: "Delete",
            success: function(data) {
                alert("刪除成功！");
                window.location="/redirectToGithub";
            },
            error: function() {
                alert("失敗");
            }
        });
    }
}
</script>
</head>

<body class = "text-center" >
<nav class="navbar navbar-expand-md navbar-light fixed-top bg-light">
    <a class="navbar-brand" href="#">
        <img src="https://scontent.ftpe7-4.fna.fbcdn.net/v/t1.0-9/95380612_676097776458143_8037565861743034368_o.jpg?_nc_cat=105&_nc_sid=ca434c&_nc_ohc=ppfBVPC8rvsAX_HQcOt&_nc_ht=scontent.ftpe7-4.fna&oh=034e343b0ab6290099533ab3399249b1&oe=5F0CCAC1" width="50" height="50" alt="">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">

        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" target="_blank" href="https://www.youtube.com/watch?v=MKRjq36didE" title="不要點......">一口炸雞一口可樂 <span class="sr-only">(current)</span></a>
            </li>
        </ul>
        <#if githubUserName??>
        <span class="navbar-text">
            ${githubUserName}，你好
    </span>
    </#if>
    </div>
</nav>	  
<main role="main">

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
        <div class="container">
            <br>
            <h1 class="display-3">至尊血壓記錄寶</h1>
            <br>
            <div class="row">
                <div class='col-md-6'>
                <h4 class="display-6">正確測量血壓方法</h4>
                <h6>1.坐在有靠背的椅子上，手臂支撐在與心臟同高之位置，並且應於量血壓前三十分禁止抽煙及攝取含咖啡因飲料。</h6>
                <h6>2.測量前必須休息五分鐘。</h6>
                <h6>3.選用適當大小之血壓加壓帶。</h6>
                <h6>4.收縮壓及舒張壓均需詳細記錄。</h6>
                <h6>5.兩次或兩次以上之測量結果，求取平均值。並且兩次測量間必須間隔兩分鐘以上。</h6>
                </div><div class='col-md-6'>
                <h4 class="display-6">血壓控制目標</h4>
                <table border="0" height="190" style="height: 190px; margin-left: auto; margin-right: auto;">
                    <tbody>
                    <tr>
                    <td style="width: 50%; text-align: center;">
                    <p><span class="TextMobileSize08rem"><strong>種類</strong></span></p>
                    </td>
                    <td style="width: 25%; text-align: center;">
                    <p><span class="TextMobileSize08rem"><strong>收縮壓</strong></span></p>
                    </td>
                    <td style="width: 389px; text-align: center;">
                    <p><span class="TextMobileSize08rem"><strong>舒張壓</strong></span></p>
                    </td>
                    </tr>
                    <tr>
                    <td style="width: 210px;">
                    <p><span class="TextMobileSize08rem">一般民眾</span></p>
                    </td>
                    <td style="width: 159px; text-align: center;">
                    <p><span class="TextMobileSize08rem">&nbsp;＜140</span></p>
                    </td>
                    <td style="width: 389px; text-align: center;">
                    <p><span class="TextMobileSize08rem">&nbsp;＜90</span></p>
                    </td>
                    </tr>
                    <tr>
                    <td style="width: 210px;">
                    <p><span class="TextMobileSize08rem">80歲以上</span></p>
                    </td>
                    <td style="width: 159px; text-align: center;">
                    <p><span class="TextMobileSize08rem">&nbsp;＜140</span></p>
                    </td>
                    <td style="width: 389px; text-align: center;">
                    <p><span class="TextMobileSize08rem">&nbsp;＜90</span></p>
                    </td>
                    </tr>
                    <tr>
                    <td style="width: 210px;">
                    <p><span class="TextMobileSize08rem">中風及慢性腎病變患者</span></p>
                    </td>
                    <td style="width: 159px; text-align: center;">
                    <p><span class="TextMobileSize08rem">&nbsp;＜140</span></p>
                    </td>
                    <td style="width: 389px; text-align: center;">
                    <p><span class="TextMobileSize08rem">&nbsp;＜90</span></p>
                    </td>
                    </tr>
                    <tr>
                    <td style="width: 210px;">
                    <p><span class="TextMobileSize08rem">糖尿病、冠心病、慢性腎病變合併蛋白尿、正在服用抗血栓藥物預防中風</span></p>
                    </td>
                    <td style="width: 159px; text-align: center; vertical-align: middle;">
                    <p><span class="TextMobileSize08rem">&nbsp;＜130</span></p>
                    </td>
                    <td style="width: 389px; text-align: center; vertical-align: middle;">
                    <p><span class="TextMobileSize08rem">&nbsp;＜80</span></p>
                    </td>
                    </tr>
                    </tbody>
                    </table>
            </div>
        </div>
        </div>
    </div>
    <div class="container">
        <!-- Example row of columns -->
        <table class="table">
            <thead class = "thead-dark">
                <tr>
                    <th scope="col">收縮壓</th>
                    <th scope="col">舒張壓</th>
                    <th scope="col">脈搏壓</th>
                    <th scope="col">測量時間</th>
                    <th scope="col">備註</th>
                    <th scope="col">刪除</th>
                </tr>
            </thead>
            <tbody>
                <#if githubUser??>
                    <#list githubUser as BloodPressure>
                    <tr>
                    <#if BloodPressure.systolic gt 140>
                    <td><font color="red">${BloodPressure.systolic}</font></td>
                    <#else>
                    <td>${BloodPressure.systolic}</td>
                    </#if>
                    <#if BloodPressure.diastolic gt 90>
                    <td><font color="red">${BloodPressure.diastolic}</font></td>
                    <#else>
                    <td>${BloodPressure.diastolic}</td>
                    </#if>
		            <td>${BloodPressure.pulse}</td>
		            <td>${BloodPressure.date}</td>
                    <#if BloodPressure.systolic gt 160 && BloodPressure.diastolic gt 110 >
                    <td>不能再吃炸雞了辣...</td>
                    <#elseif BloodPressure.systolic gt 160 || BloodPressure.diastolic gt 110>
                    <td>怎麽會這樣...</td>
                    <#elseif BloodPressure.systolic gt 140 && BloodPressure.diastolic gt 90>
                    <td>有點瓜張噢</td>
                    <#elseif BloodPressure.systolic gt 140 || BloodPressure.diastolic gt 90>
                    <td>有點危險噢</td>
                    <#else>
                    <td>哎喲不錯噢</td>
                    </#if>
                    <td><button type="button" class="btn btn-danger btn-sm" onclick="deleteBP('${BloodPressure.githubID}','${BloodPressure.date}')">刪除</button></td>
                    </tr>
                    </#list>
                </#if>
            </tbody>
            <br><button type="button" class="btn btn-lg btn-secondary btn-block" data-toggle="modal"data-target="#addBP">新增血壓量測</button><br>
        </table>
    </div>
</main>
<div class="modal fade" id="addBP" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" >新增血壓量測</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <label class="col-form-label">請輸入要新增之收縮壓：</label>
                                        <br>
                                        <input type="text" id="userSystolic" required>
                                        <br>
                                        <label class="col-form-label">請輸入要新增之舒張壓：</label>
                                        <br>
                                        <input type="text" id="userDiastolic" required>
                                        <br>
                                        <label class="col-form-label">請輸入要新增之脈搏壓：</label>
                                        <br>
                                        <input type="text" id="userPulse" required>
                                        <br>
                                        <label class="col-form-label">請輸入要新增之時間：</label>
                                        <br>
                                        <input type="text" id="userDate" placeholder="YYYY/MM/DD/HR:MI" required>

                                    </div>
                                    <div class="modal-footer">
                                        <input class="btn btn-primary" data-dismiss="modal" id="newBPButton"
                                               type="submit"/>
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                                        </button>
                                        <!-- <button type="submit" class="btn bg-primary" id="upload">新增</button> -->
                                    </div>
                                </div>
                            </div>
                        </div>
<footer class="container">
    <p>&copy; TimSyu2020</p>
</footer>

<!-- 
<#if githubUser??>
	<#list githubUser as BloodPressure>
		<h2>${BloodPressure.githubID}
		<h2>${BloodPressure.githubName}
		<h2>${BloodPressure.systolic}
		<h2>${BloodPressure.diastolic}
		<h2>${BloodPressure.pulse}
		<h2>${BloodPressure.date}
	</#list>
</#if> -->

</body>
</html>
