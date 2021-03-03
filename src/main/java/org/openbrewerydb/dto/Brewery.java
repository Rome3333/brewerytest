package org.openbrewerydb.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Brewery {

    private long id;
    private String name;
    private String brewery_type;
    private String street;
    private String address_2;
    private String address_3;
    private String city;
    private String state;
    private String county_province;
    private String postal_code;
    private String country;
    private String longitude;
    private String latitude;
    private String phone;
    private String website_url;
    private String updated_at;
    private String created_at;

}
