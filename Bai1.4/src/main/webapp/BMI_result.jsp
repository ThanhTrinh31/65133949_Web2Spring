<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<title>Kết quả BMI</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>

body {
    font-family: "Segoe UI", Arial, sans-serif;
    background: linear-gradient(135deg,#667eea,#764ba2);
    height:100vh;
    display:flex;
    align-items:center;
    justify-content:center;
}

.card {
    background:white;
    width:420px;
    padding:30px;
    border-radius:18px;
    box-shadow:0 20px 40px rgba(0,0,0,0.25);
    text-align:center;
    animation: fadeIn .6s ease;
}

@keyframes fadeIn {
    from {opacity:0; transform:translateY(20px);}
    to {opacity:1; transform:translateY(0);}
}

h2 {
    margin-bottom:15px;
}

.bmi-number {
    font-size:42px;
    font-weight:bold;
    color:#4f46e5;
    margin:10px 0;
}

.badge {
    display:inline-block;
    padding:8px 16px;
    border-radius:999px;
    font-weight:600;
    background:#eef2ff;
    color:#4338ca;
    margin-bottom:20px;
}

/* progress bar */
.progress {
    height:14px;
    background:#eee;
    border-radius:999px;
    overflow:hidden;
    margin:15px 0 25px;
}

.bar {
    height:100%;
    width:${bmi * 3}%;
    background:linear-gradient(90deg,#667eea,#764ba2);
}

/* button */
.btn {
    display:inline-block;
    padding:12px 18px;
    background:#667eea;
    color:white;
    border-radius:10px;
    text-decoration:none;
    font-weight:500;
}

.btn:hover {
    opacity:.9;
}

.note {
    font-size:13px;
    color:#777;
    margin-top:12px;
}

</style>
</head>

<body>

<div class="card">

    <h2>Kết quả BMI</h2>

    <div class="bmi-number">${bmi}</div>

    <div class="badge">${rank}</div>

    <div class="progress">
        <div class="bar"></div>
    </div>

    <a href="BMI" class="btn">Tính lại</a>

    <div class="note">
        Chuẩn BMI: 18.5 – 24.9 là bình thường
    </div>

</div>

</body>
</html>
