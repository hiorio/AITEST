import java.util.Arrays;

public class VaRCalculation {
    
    // 포트폴리오의 Value at Risk (VaR)을 계산하는 메서드
    public static double calculateVaR(double[] portfolioReturns, double confidenceLevel) {
        // 포트폴리오 수익률을 내림차순으로 정렬
        Arrays.sort(portfolioReturns);
        
        // 포트폴리오 수익률의 인덱스 계산
        int index = (int) Math.ceil(portfolioReturns.length * (1 - confidenceLevel));
        
        // VaR 값 계산
        double var = portfolioReturns[index];
        return var;
    }
    
    public static void main(String[] args) {
        // 예시로 주어진 포트폴리오 수익률 데이터
        double[] portfolioReturns = {-0.02, -0.01, -0.005, 0, 0.01, 0.015, 0.02, 0.025, 0.03, 0.035};
        double confidenceLevel = 0.95; // 신뢰수준 (95%)
        
        // VaR 계산
        double var = calculateVaR(portfolioReturns, confidenceLevel);
        System.out.println("Value at Risk (VaR) at " + (1 - confidenceLevel) * 100 + "% confidence level: " + var);
    }
}
