<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 메인 -->
<header class="masthead d-flex">
	<div class="container text-center my-auto">
		<h1 class="mb-1">유생의 개인 공부방</h1>
		<h3 class="mb-5">
			<em>공부용으로 만듬 ㅇㅇ</em>
		</h3>
		<a class="btn btn-primary btn-xl js-scroll-trigger" href="#portfolio">게시판</a>
	</div>
	<div class="overlay"></div>
</header>

<!-- 게시판 모음 -->
<section class="content-section" id="portfolio">
	<div class="container">
		<div class="content-section-heading text-center">
			<h3 class="text-secondary mb-0">Portfolio</h3>
			<h2 class="mb-5">게시판 모음</h2>
		</div>
		<div class="row no-gutters">
			<div class="col-lg-6">
				<a class="portfolio-item" href="#">
					<span class="caption">
						<span class="caption-content">
							<h2>웹진형 게시판</h2>
							<p class="mb-0">웹진형으로 섬네일, 제목, 내용을 한눈에 볼 수 있게 만들어진 게시판</p>
						</span>
					</span>
					<img class="img-fluid" src="/resources/img/portfolio-1.jpg" alt="">
				</a>
			</div>
			<div class="col-lg-6">
				<a class="portfolio-item" href="#">
					<span class="caption">
						<span class="caption-content">
							<h2>미정 게시판</h2>
							<p class="mb-0">아직 미정</p>
						</span>
					</span>
					<img class="img-fluid" src="/resources/img/portfolio-2.jpg" alt="">
				</a>
			</div>
			<div class="col-lg-6">
				<a class="portfolio-item" href="#">
					<span class="caption">
						<span class="caption-content">
							<h2>미정 게시판</h2>
							<p class="mb-0">아직 미정</p>
						</span>
					</span>
					<img class="img-fluid" src="/resources/img/portfolio-3.jpg" alt="">
				</a>
			</div>
			<div class="col-lg-6">
				<a class="portfolio-item" href="#">
					<span class="caption">
						<span class="caption-content">
							<h2>미정 게시판</h2>
							<p class="mb-0">아직 미정</p>
						</span>
					</span>
					<img class="img-fluid" src="/resources/img/portfolio-4.jpg" alt="">
				</a>
			</div>
		</div>
	</div>
</section>

<!-- 최상단 이동 버튼-->
<a class="scroll-to-top rounded js-scroll-trigger" href="#page-top">
	<i class="fas fa-angle-up"></i>
</a>

<script type="text/javascript">
	var blockTF = "${blockTF}";
	if(blockTF == "true"){
		alert("잘못된 접근입니다.");
	}
</script>