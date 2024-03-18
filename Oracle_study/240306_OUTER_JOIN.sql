-- 지금까지 사용해왔던 Join은 inner Join임
-- Outer Join이란?
SELECT * FROM dept;
-- 40번 부서는 한번도 언급되지 않았음
SELECT * FROM emp;
-- emp table에는 10, 20, 30번 부서만 존재한다
--
-- 부서와 사원의 join
SELECT d.*, e.*
FROM dept d, emp e
WHERE d.DEPTNO = e.DEPTNO
;
-- inner join은 양 테이블에 공통되어 있는 정보만 긁어오기때문에
-- 40번 지부 Boston은 출력되지 않는다
-- 이때 outer join을 사용함
-- outer join: 공통적이지 않은 레코드까지도 가져오는 join
--
-- SELECT
-- FROM 무조건 가져올 테이블 명 LEFT OUTER JOIN 조인 대상 테이블
-- ON
--
SELECT d.*, e.* 
FROM dept d LEFT OUTER JOIN emp e
ON d.deptno = e.deptno
;
-- 개발 공부 시 공통적이지 않은 레코드도 함께 join해야 할 일이 생김
SELECT DNAME, COUNT(E.DEPTNO)
FROM DEPT D LEFT OUTER JOIN EMP E
ON D.DEPTNO = E.DEPTNO
GROUP BY DNAME
;
-- inner join 시, 공통적인 레코드만 가져오므로 자식이 존재하지 않는 부모레코드의 경우 누락될 수 있음
SELECT DNAME, COUNT(E.DEPTNO)
FROM DEPT D, EMP E
WHERE D.DEPTNO = E.DEPTNO
GROUP BY DNAME
;
--
SELECT * FROM TOPCATEGORY;
SELECT * FROM SUBCATEGORY;
-- 사업 확장으로 인해 상의/하의/악세서리를 넘어 신발류가 추가
INSERT INTO TOPCATEGORY(TOPCATEGORY_IDX, TOPNAME)
VALUES(SEQ_TOPCATEGORY.NEXTVAL, 'SHOES')
;
SELECT * FROM TOPCATEGORY;
-- 정렬
SELECT * FROM TOPCATEGORY ORDER BY TOPCATEGORY_IDX ASC;
-- 신발은 아직 입고가 안되있으므로
-- TOPNAME별 자식이 몇 개씩 있는지 통계를 내본다
-- 먼저 JOIN
SELECT T.*, S.*
FROM TOPCATEGORY T , SUBCATEGORY S 
WHERE T.TOPCATEGORY_IDX = S.TOPCATEGORY_IDX
;
-- GROUP BY를 이용한 통계
SELECT TOPNAME, COUNT(S.TOPCATEGORY_IDX)
FROM TOPCATEGORY T, SUBCATEGORY S
WHERE T.TOPCATEGORY_IDX = S.TOPCATEGORY_IDX
GROUP BY TOPNAME
;
-- 신발류가 없기에 OUTER JOIN
SELECT T.*, S.*
FROM TOPCATEGORY T LEFT OUTER JOIN SUBCATEGORY S
ON T.TOPCATEGORY_IDX = S.TOPCATEGORY_IDX
;
-- 통계
SELECT TOPNAME, COUNT(S.TOPCATEGORY_IDX)
FROM TOPCATEGORY T LEFT OUTER JOIN SUBCATEGORY S
ON T.TOPCATEGORY_IDX = S.TOPCATEGORY_IDX 
GROUP BY TOPNAME
;
-- VIEW: 새 테이블로 물리적으로 저장
-- 시스템에서 GRANT CREATE VIEW TO WENDY;로 권한 부여
CREATE VIEW OUTER_JOIN_VIEW
AS
SELECT TOPNAME, COUNT(S.TOPCATEGORY_IDX) AS CNT
FROM TOPCATEGORY T LEFT OUTER JOIN SUBCATEGORY S
ON T.TOPCATEGORY_IDX = S.TOPCATEGORY_IDX 
GROUP BY TOPNAME
;
-- 조회
SELECT * FROM OUTER_JOIN_VIEW;
-- 사용
SELECT *
FROM OUTER_JOIN_VIEW 
WHERE CNT>2
;
