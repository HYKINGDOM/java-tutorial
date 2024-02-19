package com.java.demo.mapper;

import java.text.SimpleDateFormat;

import com.java.coco.utils.date.DateUtil;
import com.java.demo.dto.Address;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 *
 */
public class ReoportFieldSetMapper implements FieldSetMapper<Address> {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * "address_id", "address", "address2", "district", "city_id", "postal_code", "phone", "last_update"
     *
     * @param fieldSet
     * @return
     * @throws BindException
     */
    @Override
    public Address mapFieldSet(FieldSet fieldSet) throws BindException {
        Address.AddressBuilder addressBuilder = Address.builder();
        //receiptDate,memberName,checkNumber,checkDate,paymentType,depositAmount,paymentAmount,comments
        String addressId = fieldSet.readString("address_id");
        String address = fieldSet.readString("address");
        String address2 = fieldSet.readString("address2");
        String district = fieldSet.readString("district");
        String cityId = fieldSet.readString("city_id");
        String postalCode = fieldSet.readString("postal_code");
        String phone = fieldSet.readString("phone");
        String lastUpdateStr = fieldSet.readString("last_update");

        addressBuilder.addressId(Long.parseLong(addressId));
        addressBuilder.address(address);
        addressBuilder.address(address2);
        addressBuilder.address(district);
        addressBuilder.address(cityId);
        addressBuilder.address(postalCode);
        addressBuilder.address(phone);
        addressBuilder.lastUpdate(DateUtil.parse(lastUpdateStr, "yyyy-MM-dd HH:mm:ss"));

        return addressBuilder.build();
    }

}
