
package com.sds.app0229.use;

import com.sds.app0229.car.Car;

class UseCar {
	public static void main(String[] args) {
		Car car = new Car();
		
		System.out.println(car.handle.material);
		System.out.println(car.door[0].color);
		System.out.println(car.wheel[0].brand);
	}
}
