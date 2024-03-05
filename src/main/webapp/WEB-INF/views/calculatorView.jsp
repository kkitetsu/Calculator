<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calculator</title>
<link rel='stylesheet' type='text/css' href='styles/css/style.css'>
</head>
<body>
	<div class="container">
		<div class="input">
			<h1>Welcome to Calculator</h1>
			<form action="calculator" method="post">
				<input type="hidden" id="tmpInput" name="label">
				<h1 id="label">${result}</h1>
				<button class="submit_btn" type="submit">=</button>
			</form>
		</div>
		<div class="numeric">
			<button class="button" onclick="changeText('1')">1</button>
			<button class="button" onclick="changeText('2')">2</button>
			<button class="button" onclick="changeText('3')">3</button>
			<button class="button" onclick="changeText('4')">4</button>
			<button class="button" onclick="changeText('5')">5</button>
			<button class="button" onclick="changeText('6')">6</button>
			<button class="button" onclick="changeText('7')">7</button>
			<button class="button" onclick="changeText('8')">8</button>
			<button class="button" onclick="changeText('9')">9</button>
			<button class="button" onclick="changeText('0')">0</button>
		</div>
		<div class="operator">
			<button class="button" onclick="changeText( '+' )">+</button>
			<button class="button" onclick="changeText( '-' )">-</button>
			<button class="button" onclick="changeText( '*' )">*</button>
			<button class="button" onclick="changeText( '/' )">/</button>
			<button class="button" onclick="deleteLabel()">Clear</button>
		</div>
	</div>
	<script>
		function changeText(str) {
			var current_str = document.getElementById("label").textContent;
			if ((str === '+') || (str === '-') || (str === '*') || (str === '/')) {
				current_str += " " + str + " ";
			} else {
				current_str += str;
			}
			console.log(current_str);
			document.getElementById("label").textContent = current_str;
			document.getElementById("tmpInput").value = current_str;
		}
		function deleteLabel() {
			document.getElementById("label").textContent = "";
			document.getElementById("tmpInput").value  = "";
		}
	</script>
</body>
</html>