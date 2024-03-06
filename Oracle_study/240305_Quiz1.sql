-- 더미 데이터
SELECT 10+5 FROM dual;
-- 문제1
-- SCOTT의 급여에서 KING의 급여를 뺀 급여의 절대값보다 급여가 적은 사원의 이름, 급여, 부서명을 출력하시오  (절대값은 ABS 함수 이용 )
SELECT ename, sal, dname
FROM emp e, dept d
WHERE e.deptno = d.DEPTNO 
AND sal < (
	SELECT abs((SELECT sal FROM emp WHERE ename='SCOTT') - (SELECT sal FROM emp WHERE ename='KING'))
	FROM dual
);
-- 문제2
-- 20번 부서에 근무하는 사원들의 급여를 3으로 나눈 값을 구하되, 그 결과값이 소수점 N일 경우 , N보다 큰 정수 중 가장 작은 수로 출력되도록 하세요  ( CEIL 함수 이용 )
SELECT CEIL(
	(
		SELECT sum(sal)
		FROM EMP
		WHERE deptno=20
	)/3
)
FROM dual
;
-- 문제3
-- 30번 부서에 근무하는 사원들의 급여를 3으로 나눈 값을 구하되, 그 결과값이 소수점 N일 경우 , N보다 작은 정수 중 가장 큰 수로 출력되도록 하세요  ( FLOOR 함수 이용 )
--
-- 문제4
-- 10을 3으로 나눈 나머지를 출력하시오  (MOD  함수 이용 )
SELECT MOD(10, 3)
FROM dual
;
-- 문제5
-- 아래와 같이 출력하시오 ( CONCAT 함수 이용) 사원의 급여는 800 입니다
SELECT concat(concat('사원의 급여는 ', SAL),'입니다.')
FROM emp
;
-- 문제6
-- KING 사원의 이름을 QUEEN 으로 출력하시오  (REPLACE 함수 이용)
SELECT REPLACE(ename, 'KING', 'QUEEN')
FROM EMP
;
-- 문제7
-- 사원의 이름을 앞에서 3자만 출력하시오 (SUBSTR 함수 이용)
SELECT SUBSTR(ename, 0, 3) 
FROM EMP
;
-- 문제8
-- 사원의 입사일을 아래와 같은형식으로 출력하시오: 1980-12/17 (TO_CHAR 함수 이용)
SELECT to_char(HIREDATE, 'yyyy-mm/dd')
FROM EMP
;
-- 문제9
-- 아래와 같이 사원의 입사일과 그 입사일에 3개월 더한 날짜를 나란히 출력하세요 (ADD_MONTH 함수이용)
-- 80/12/17  -->   81/03/17,  81/02/20  -->   81/05/20
SELECT hiredate, ADD_MONTHS(hiredate, 3) 
FROM EMP
;
-- 문제10
-- 아래와 같이 현재 달의 마지막날을 출력하세요: 2024-03-31 ( TO_CHAR, CURRENT_DATE, LAST_DAY 함수)
SELECT to_char(LAST_DAY(CURRENT_DATE), 'YYYY-MM-DD')
FROM dual
;
-- 문제11
-- 아래와 같이 날짜를 나타내는 문자열을 날짜형으로 변환하여 출력해보세요: ‘1998-10-25’  을 1999,10,25 로 출력 (TO_DATA, TO_CHAR)
SELECT to_char(to_Date('1998-10-25', 'YYYY/MM/DD'), 'YYYY,MM,DD')
FROM dual
;
-- 문제12
-- 커미션 계약을 하지 않은 사원의 커미션 값은 -1로 대체하여 출력하세요 ( NVL 함수 이용) 
SELECT nvl(comm, -1)
FROM EMP
;
-- 문제13
-- FORD 의 입사일로부터 36개월을 더한 날짜보다 늦게 입사한 사원의 이름, 입사일을 출력하세요
SELECT ename, hiredate
FROM EMP
WHERE hiredate > (
	SELECT ADD_MONTHS(hiredate, 36)
	FROM EMP
	WHERE ename='FORD'
);
-- 문제14
-- MARTIN의 입사일의 6개월을 뺀 날짜보다도 더 이전에 입사한사원들의 이름, 입사일을 출력하세요
SELECT ename, hiredate
FROM emp
WHERE hiredate < (
	SELECT ADD_MONTHS(HIREDATE, -6)
	FROM emp
	WHERE ename='MARTIN'
);



