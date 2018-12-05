package com.n26.statistics.dto;

public class StatisticsDto {

	private Double sum;
	private Double avg;
	private Double max;
	private Double min;
	private Long count;

	public StatisticsDto() {
		super();
	}

	public StatisticsDto(Double sum, Double avg, Double max, Double min, Long count) {
		super();
		this.sum = sum;
		this.avg = avg;
		this.max = max;
		this.min = min;
		this.count = count;
	}

	/**
	 * @return the sum
	 */
	public Double getSum() {
		return sum;
	}

	/**
	 * @param sum the sum to set
	 */
	public void setSum(Double sum) {
		this.sum = sum;
	}

	/**
	 * @return the avg
	 */
	public Double getAvg() {
		return avg;
	}

	/**
	 * @param avg the avg to set
	 */
	public void setAvg(Double avg) {
		this.avg = avg;
	}

	/**
	 * @return the max
	 */
	public Double getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(Double max) {
		this.max = max;
	}

	/**
	 * @return the min
	 */
	public Double getMin() {
		return min;
	}

	/**
	 * @param min the min to set
	 */
	public void setMin(Double min) {
		this.min = min;
	}

	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}

}
