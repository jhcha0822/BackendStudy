class DataConvert{
	public static void main(String[] args){
		short s1=3;
		short s2=4;

		short sum = s1+s2;
		// 에러 발생
		// 고전 32bit 컴퓨터에서 연산 수행시 int로 바뀌어 수행
		// (int 보다 작은 byte와 short) 연산 수행 시 int로 자동 형변환
		// 64bit 컴퓨터도 동일함

		// 이를 막기 위해서는 강제 형변환 (cast) 이용
		// short sum = (short)s1 + (short)s2;
	}
	
}