package com.raebagi.order;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.raebagi.order.entity.Menu;
import com.raebagi.order.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

	private final MenuRepository menuRepository;

	@Override
	public void run(String... args) throws Exception {
		// 이미 데이터가 있다면 넣지 않음
		if (menuRepository.count() > 0)
			return;

		// --- 사시미 ---
		menuRepository.save(new Menu("모듬 사시미", 35000, "사시미", "다양한 종류의 사시미와 해산물을 즐기실 수 있는 2인용 숙성 생선회"));
		menuRepository.save(new Menu("특 모듬 사시미", 55000, "사시미", "자쿠와의 chef가 추천하는 일식 모둠 생선회의 진수 3인용 숙성 생선회"));
		menuRepository.save(new Menu("스페셜 모듬 사시미", 75000, "사시미", "광어, 참치, 연어 및 다양한 제철 생선을 넉넉하게 썰어낸 츠키지풍 스페셜 모둠 4인용 숙성 생선회"));
		menuRepository.save(new Menu("생 연어 사시미", 29000, "사시미", "풍부한 영양과 항산화 기능으로 타임지 선정 10대 슈퍼푸드인 연어 사시미"));

		// --- 스시 ---
		menuRepository.save(new Menu("오늘의 초밥 (10p)", 16000, "스시", "(참치1, 광어2, 연어2, 게살1, 왕새우1, 장새우1, 계란1, 참소라1)"));
		menuRepository.save(new Menu("스페셜 초밥 (12p)", 22000, "스시", "(참치1, 광어2, 연어2, 게살1, 도미1, 갈릭새우1, 장새우1, 꽃등심1, 장어1, 참치불초밥1)"));
		menuRepository.save(new Menu("VIP 초밥 (14p)", 29000, "스시", "(참치1, 광어1, 연어2, 도미2, 광어지느러미1, 생새우1, 장새우1, 갈릭새우1, 전복1, 관자1, 꽃등심1, 장어1)"));

		// --- 샐러드 & 과일 ---
		menuRepository.save(new Menu("메론 & 파인애플", 19000, "샐러드&과일", "고당도 머스크 메론 & 골드 파인애플과의 환상 랑데뷰!"));
		menuRepository.save(new Menu("토마토 크림치즈 샐러드", 21000, "샐러드&과일", "후레쉬 토마토와 진한 크림치즈가 아삭한 야채와 오리엔탈 소스에 어우러진 일품 샐러드"));
		menuRepository.save(new Menu("크림치즈 연어 샐러드", 23000, "샐러드&과일", "그라나파다노와 크림치즈를 곁들인 생연어에 상큼한 이탈리안 드레싱과 발사믹 소스의 환상궁합!"));

		// --- 초회요리(스노모노) ---
		menuRepository.save(new Menu("문어 초회", 23000, "초회요리(스노모노)", "쫄깃한 문어를 특제 '유자 폰즈 소스' 로 어레인지한 초회요리"));
		menuRepository.save(new Menu("센다이 소고기 타다끼", 23000, "초회요리(스노모노)", "1등급 소고기를 직화로 살짝 익혀 특급 '삼바이스 폰즈 소스' 로 감칠맛을 더한 요리"));
		menuRepository.save(new Menu("연어 타다끼", 24000, "초회요리(스노모노)", "부드러운 식감의 연어를 살짝 익해 자쿠와 특제소스로 감칠맛을 더한 요리"));

		// --- 구이요리(야끼모노) ---
		menuRepository.save(new Menu("고등어 구이", 20000, "구이요리(야끼모노)", "DHA와 불포화 지방산이 다량 함유된 건강에 좋은 등푸른 생선의 대명사!"));
		menuRepository.save(new Menu("연어 머리 구이", 20000, "구이요리(야끼모노)", "불포화 지방산이 다량 함유된 피부 미용에 좋은 연어 머리를 맛깔나게 구워낸 요리"));
		menuRepository.save(new Menu("메로 구이(소금)", 28000, "구이요리(야끼모노)", "1,500m 아래의 심해에 서식하는 바다의 명품 메로를 담백하게 구워낸 요리"));
		menuRepository.save(new Menu("메로 구이(데리)", 28000, "구이요리(야끼모노)", "1,500m 아래의 심해에 서식하는 바다의 명품 메로를 담백하게 구워낸 요리"));

		// --- 철판요리(데판야끼) ---
		menuRepository.save(new Menu("생삼겹 숙주볶음", 21000, "철판요리(데판야끼)", "생삼겹살과 아삭한 숙주나물을 야끼타래와 함께 센불에 빠르게 볶아낸 요리"));
		menuRepository.save(new Menu("화통 오돌뼈&날치알 주먹밥", 21000, "철판요리(데판야끼)", "화끈하게 불맛나는 오돌뼈 볶음과 허기를 달래주는 날치알 주먹밥의 만남!"));
		menuRepository.save(new Menu("해물 야끼우동", 22000, "철판요리(데판야끼)", "다양한 해산물과 탱글탱글한 저각감의 사누끼 우동면을 특제소스에 빠르게 볶아낸 요리"));
		menuRepository.save(new Menu("치즈 오꼬노미야끼", 22000, "철판요리(데판야끼)", "산마를 갈아 반죽한 도우에 해물과 숙주, 양배추의 아삭한 식감이 어우러진 야끼 대표요리"));
		menuRepository.save(new Menu("게살치즈 계란말이 (다마고아끼)", 22000, "철판요리(데판야끼)", "오사카 도톤보리의 명물! 게살과 모짜렐라 치즈가 듬뿍 들어간 부드러운 식감의 에그요리"));
		menuRepository.save(new Menu("오쭈 불고기 한판", 24000, "철판요리(데판야끼)", "신선한 오징어와 쭈꾸미, 삼겹살을 자쿠와만의 숙성 고추장 소스에 맛깔나게 볶아낸 철판요리"));

		// --- 튀김요리(아게모노) ---
		menuRepository.save(new Menu("자쿠와 모둠 고로케", 17000, "튀김요리(아게모노)", "고로케의 나라 일본의 현지방식 그대로 만든 진심을 담은 후라이 요리"));
		menuRepository.save(new Menu("이까게소와 새우깡", 21000, "튀김요리(아게모노)", "쫄깃한 오징어 다리와 통통한 민물 새우를 바삭바삭하게 튀겨낸 최고의 맥주 안주"));
		menuRepository.save(new Menu("갈릭치킨 가라아게", 22000, "튀김요리(아게모노)", "부드러운 닭다리살에 가라아게 파우더 옷을 입혀 자쿠와 특제 갈릭소스로 버무려낸 일본식 닭 튀김요리"));
		menuRepository.save(new Menu("모둠 가라아게", 28000, "튀김요리(아게모노)", "자쿠와의 일품 튀김요리를 전부 맛보자! 고로케, 닭다리살, 이까게소, 롱칩등의 가라아게가 한접시에!"));
		menuRepository.save(new Menu("자쿠와 칠리새우", 23000, "튀김요리(아게모노)", "자쿠와 특제 칠리소스를 이용해 중화팬에서 불맛나게 볶아낸 새우요리"));
		menuRepository.save(new Menu("자쿠와 새우튀김", 23000, "튀김요리(아게모노)", "얇고 바삭한 튀김옷을 입혀 깔끔한 식감을 자랑하는 새우튀김과 멘다시의 찰떡궁합"));

		// --- 전골요리(나베모노) ---
		menuRepository.save(new Menu("자쿠와 스키야끼", 14000, "전골요리(나베모노)", "다양한 야채와 버섯, 어묵, 유부주머니 등 영양분이 풍부한 재료로 맛을 낸 일본 관서풍의 소 등심 나베요리 (2인 이상 주문가능, 우동사리 포함)"));
		menuRepository.save(new Menu("자쿠와 모츠나베", 29000, "전골요리(나베모노)", "진하게 우려낸 사골육수에 자쿠와만의 비법 매콤소스가 어우러진 일식 소 곱창 전골요리"));
		menuRepository.save(new Menu("해물치즈 돈뽀끼", 24000, "전골요리(나베모노)", "두툼한 사이즈의 치즈 돈까스와 칼칼한 해물 떡볶이와의 맛있는 랑데뷰"));
		menuRepository.save(new Menu("모둠 조개 술찜", 23000, "전골요리(나베모노)", "다양한 제철 조개에 사케 베이스의 버터 스톡을 부어 끓어낸 최고의 술 안주거리"));
		menuRepository.save(new Menu("간사이 오뎅나베", 23000, "전골요리(나베모노)", "다채로운 수제 가마보코 어묵이 어우러진 정통 간사이 스타일의 나베요리"));
		menuRepository.save(new Menu("수제비 해산물 짬뽕탕", 23000, "전골요리(나베모노)", "신선한 해산물을 불맛나게 볶아내어 화끈한 매운맛을 자랑하는 감자 수제비 짬뽕탕"));
		menuRepository.save(new Menu("나가사끼 짬뽕나베", 23000, "전골요리(나베모노)", "진한 돈코츠 육수와 해산물, 숙주가 어우러진 일본 나가사끼현의 대표 나베요리 (면사리 별도주문)"));
		menuRepository.save(new Menu("얼큰 해물 우육탕", 23000, "전골요리(나베모노)", "소고기와 해물로 우려낸 진한 국물과 여운이 남는 얼큰함의 조화"));
		menuRepository.save(new Menu("모둠 조개탕", 23000, "전골요리(나베모노)", "제철 모둠조개와 어패류 스톡에 청양고추를 가미해 시원하고 칼칼한 맑은 탕요리"));

		// --- 식사류 ---
		menuRepository.save(new Menu("모둠 회덮밥", 13000, "식사류", "신선한 모둠 회와 야채를 초고추장에 비벼 먹는 식사 메뉴"));
		menuRepository.save(new Menu("돈까스 덮밥 (카츠동)", 11000, "식사류", "두툼한 돈까스와 특제 소스, 계란이 어우러진 든든한 덮밥 요리"));
		menuRepository.save(new Menu("생연어 덮밥 (사케동)", 14000, "식사류", "부드러운 생연어를 특제 간장소스와 함께 즐기는 대표 연어 덮밥"));
		menuRepository.save(new Menu("오삼 불고기 덮밥", 11000, "식사류", "매콤달콤한 오징어와 삼겹살 불고기를 얹어낸 든든한 덮밥"));
		menuRepository.save(new Menu("해물 짬뽕라면", 11000, "식사류", "얼큰하고 진한 해물 국물에 면을 담아낸 해장 라면"));
		menuRepository.save(new Menu("자쿠와 육칼 (육개장 칼국수)", 12000, "식사류", "진한 육개장 국물에 쫄깃한 칼국수 면이 들어간 별미"));
		menuRepository.save(new Menu("사리추가 (라면, 우동, 수제비)", 2000, "식사류", "나베나 탕 등에 추가하는 각종 사리 (※메뉴판 표기 기준)")); // 2000원은 0.2이므로 2000원으로 조정 필요시 수정
		menuRepository.save(new Menu("햇반", 2000, "식사류", "따끈한 즉석 공기밥"));


		// --- 간단요리 ---
		menuRepository.save(new Menu("미니 모둠 감자튀김", 12000, "간단요리", "가볍게 집어먹기 좋은 바삭한 모둠 감자튀김"));
		menuRepository.save(new Menu("피데기 (반건조 오징어)", 14000, "간단요리", "쫄깃하고 촉촉하게 반건조한 오징어 구이"));
		menuRepository.save(new Menu("새우깡", 9000, "간단요리", "바삭하게 튀겨내어 자꾸만 손이 가는 고소한 안주"));
		menuRepository.save(new Menu("타코야끼", 9000, "간단요리", "겉바속촉 문어가 쏙쏙 들어간 타코야끼"));
		menuRepository.save(new Menu("파인애플 샤베트", 9000, "간단요리", "상큼달콤 시원하게 입가심하기 좋은 파인애플 샤베트"));
		menuRepository.save(new Menu("타코와사비", 13000, "간단요리", "알싸한 와사비와 쫄깃한 낙지의 톡 쏘는 별미 안주"));
		menuRepository.save(new Menu("메론 샤베트", 10000, "간단요리", "달콤하고 부드러운 메론 맛 시원한 샤베트"));
		menuRepository.save(new Menu("토닉세트 레몬", 4000, "간단요리", "토닉워터2병 레몬슬라이스1개"));

		//주류
		menuRepository.save(new Menu("참이슬", 5500, "주류", ""));
		menuRepository.save(new Menu("처음처럼", 5500, "주류", ""));
		menuRepository.save(new Menu("청하", 6000, "주류", ""));
		menuRepository.save(new Menu("매화수", 6000, "주류", ""));
		menuRepository.save(new Menu("한라산", 6000, "주류", ""));
		menuRepository.save(new Menu("별빛청하", 6000, "주류", ""));
		menuRepository.save(new Menu("진로", 5500, "주류", ""));
		menuRepository.save(new Menu("새로", 5500, "주류", ""));
		menuRepository.save(new Menu("카스", 6000, "주류", ""));
		menuRepository.save(new Menu("테라", 6000, "주류", ""));
		menuRepository.save(new Menu("켈리", 6000, "주류", ""));
		menuRepository.save(new Menu("카스 레몬(무알콜)", 4000, "주류", ""));

		//생맥주
		menuRepository.save(new Menu("테라 생 500", 5500, "생맥주", ""));
		menuRepository.save(new Menu("테라 생 17000", 16500, "생맥주", ""));
		menuRepository.save(new Menu("삿포로 생맥주", 7500, "생맥주", ""));
		menuRepository.save(new Menu("기린 이치방 생맥주", 7500, "생맥주", ""));

		//도쿠리,잔술
		menuRepository.save(new Menu("대포잔", 9000, "잔술", ""));
		menuRepository.save(new Menu("도쿠리", 14000, "도쿠리", "기본으로 따뜻하게 나갑니다"));
		menuRepository.save(new Menu("냉도쿠리", 16000, "도쿠리", ""));
		//사케
		menuRepository.save(new Menu("하나 기자쿠라", 57000, "사케", ""));
		menuRepository.save(new Menu("쿠츠로기노 아마구치", 42000, "사케", ""));
		menuRepository.save(new Menu("간바레 오돗짱", 42000, "사케", ""));
		menuRepository.save(new Menu("요하찌로(대용량)", 57000, "사케", ""));
		menuRepository.save(new Menu("구보타 센쥬", 79000, "사케", ""));
		menuRepository.save(new Menu("네노히마츠 준마이 다이긴죠", 99000, "사케", ""));
		menuRepository.save(new Menu("월계관 준마이 다이긴죠", 79000, "사케", ""));
		menuRepository.save(new Menu("구보타 만쥬", 229000, "사케", ""));
		menuRepository.save(new Menu("키쥬로(대용량)", 92000, "사케", ""));
		menuRepository.save(new Menu("사와노츠루 준마이", 47000, "사케", ""));
		menuRepository.save(new Menu("하쿠시카 준마이", 42000, "사케", ""));
		menuRepository.save(new Menu("쥰마이 10.5", 47000, "사케", ""));
		menuRepository.save(new Menu("쥰마이 750", 42000, "사케", ""));
		menuRepository.save(new Menu("모모카와 준마이", 42000, "사케", ""));
		menuRepository.save(new Menu("누벨 쥰마이", 57000, "사케", ""));

		//본격 소주
		menuRepository.save(new Menu("화요 17도", 27000, "본격 소주", ""));
		menuRepository.save(new Menu("화요 25도", 29000, "본격 소주", ""));
		menuRepository.save(new Menu("일품진로", 30000, "본격 소주", ""));
		menuRepository.save(new Menu("비잔 클리어", 59000, "본격 소주", ""));
		//하이볼
		menuRepository.save(new Menu("짐빔 샷", 5000, "하이볼", ""));
		menuRepository.save(new Menu("산토리 가쿠빈 샷", 5000, "하이볼", ""));
		menuRepository.save(new Menu("산토리 가쿠빈 하이볼", 8000, "하이볼", ""));
		menuRepository.save(new Menu("짐빔 하이볼", 8000, "하이볼", ""));
		menuRepository.save(new Menu("산토리 메가 하이볼", 14000, "하이볼", ""));
		menuRepository.save(new Menu("짐빔 메가 하이볼", 14000, "하이볼", ""));
		menuRepository.save(new Menu("수이진 샷", 5000, "하이볼", ""));
		menuRepository.save(new Menu("산토리 수이진 하이볼", 9000, "하이볼", ""));
		menuRepository.save(new Menu("치타 샷", 12000, "하이볼", ""));
		menuRepository.save(new Menu("치타 하이볼", 13000, "하이볼", ""));
		menuRepository.save(new Menu("메이커스마크 하이볼", 11000, "하이볼", ""));
		menuRepository.save(new Menu("메이커스마크 샷", 7000, "하이볼", ""));
		menuRepository.save(new Menu("오켄토션 샷", 7000, "하이볼", ""));
		menuRepository.save(new Menu("오켄토션 하이볼", 12000, "하이볼", ""));
		menuRepository.save(new Menu("메이커스마크 세트", 33000, "하이볼", ""));
		menuRepository.save(new Menu("야마자키 하이볼", 22000, "하이볼", ""));
		menuRepository.save(new Menu("야마자키 샷", 20000, "하이볼", ""));
		menuRepository.save(new Menu("히비키 하모니 하이볼", 20000, "하이볼", ""));
		menuRepository.save(new Menu("히비키 하모니 샷", 18000, "하이볼", ""));




		//음료
		menuRepository.save(new Menu("제로 콜라", 2000, "음료", ""));
		menuRepository.save(new Menu("코카 콜라", 2000, "음료", ""));
		menuRepository.save(new Menu("사이다", 2000, "음료", ""));
		menuRepository.save(new Menu("환타 파인", 2000, "음료", ""));
		menuRepository.save(new Menu("토닉워터", 2500, "음료", ""));
		//직원 호출
		menuRepository.save(new Menu("숟가락", 0, "직원 호출", ""));
		menuRepository.save(new Menu("젓가락", 0, "직원 호출", ""));
		menuRepository.save(new Menu("앞치마", 0, "직원 호출", ""));
		menuRepository.save(new Menu("담요", 0, "직원 호출", ""));
		menuRepository.save(new Menu("냅킨", 0, "직원 호출", ""));
		menuRepository.save(new Menu("물티슈", 0, "직원 호출", ""));
		menuRepository.save(new Menu("물", 0, "직원 호출", ""));
		menuRepository.save(new Menu("물컵", 0, "직원 호출", ""));
		menuRepository.save(new Menu("소주잔", 0, "직원 호출", ""));
		menuRepository.save(new Menu("맥주잔", 0, "직원 호출", ""));
		menuRepository.save(new Menu("고체연료", 0, "직원 호출", ""));
		menuRepository.save(new Menu("직원 호출", 0, "직원 호출", ""));
		System.out.println(">>> 초기 메뉴 데이터 세팅 완료!");
	}
}