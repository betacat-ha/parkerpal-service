package cn.com.betacat.parkerpal.domain.entity;

import cn.com.betacat.parkerpal.domain.enums.BaseStationTypeEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * 基站实体类
 *
 * @author BetaCat
 * @since 2025-4-2 16:35:00
 * @version  0.0.1
 */
@Data
public class BaseStation implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	* 基站ID
	*/
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	/**
	 * 传感器ID
	 */
	@TableField(value = "iot_device_id")
	private String iotDeviceId;

	/**
	 * 基站类型
	 */
	@TableField(value = "type")
	private String type;
	
	/**
	 * 基站坐标X轴
	 */
	@TableField(value = "x_axis")
	private Double xAxis;

	/**
	 * 基站坐标Y轴
	 */
	@TableField(value = "y_axis")
	private Double yAxis;

	@TableField(exist = false)
	private IotDevice iotDevice;
	
	public BaseStation(String id, BaseStationTypeEnum typeEnum, Double xAxis, Double yAxis) {
		super();
		this.id = id;
		this.type = typeEnum.getTypeId();
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}

	public BaseStation() {
		
	}

	@Override
	public String toString() {
		return "该基站id为：" + id + "，传感器id" + iotDeviceId + "，x轴坐标为：" + xAxis + "，y轴坐标为:" + yAxis + "]";
	}
	
	
}