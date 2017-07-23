package com.ineuron.domain.device.repository;

import com.ineuron.common.exception.RepositoryException;
import com.ineuron.dataaccess.db.INeuronDBConnection;
import com.ineuron.domain.device.entity.Device;
import com.ineuron.domain.device.valueobject.DeviceAttribute;
import com.ineuron.domain.device.valueobject.DeviceAttributeCategory;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeviceRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeviceRepository.class);

	public DeviceRepository() {

	}

	public void addDevice(Device device) throws RepositoryException {
		SqlSession session = INeuronDBConnection.getSession();
		/*try {
			int deviceSN = 0;
			Device p = session.selectOne("getMaxDeviceSNByCategoryId", device.getDeviceCategoryId());
			if (p != null)
				deviceSN = p.getSerialNumber() + 1;
			else
				deviceSN = 1;
			device.setSerialNumber(deviceSN);
			// System.out.println("deviceSN: "+deviceSN);
			String deviceCode = device.getCode() + "-" + String.format("%03d", deviceSN);
			device.setCode(deviceCode);

			System.out.println("device code: " + deviceCode);
			session.insert("addDevice", device);
			session.commit();
			System.out.println("insert device by using mybatis!");
		} finally {
			session.close();
		}*/
	}

	public void deleteDevice(Device device) throws RepositoryException {
		SqlSession session = INeuronDBConnection.getSession();
		try {
			session.delete("deleteDevice", device);
			session.commit();
			System.out.println("delete device by using mybatis!");
		} finally {
			session.close();
		}
	}

	

}
