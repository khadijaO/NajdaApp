package com.example.najdaapp.contact;

public class ContactModel {
    private int id;
    private String phoneNo;
    private String name;
    private String relation;

    public ContactModel( String phoneNo, String name, String relation) {

        this.phoneNo = phoneNo;
        this.name = name;
        this.relation = relation;
    }

    private String validate(String phone) {

        // creating StringBuilder for both the cases
        StringBuilder case1 = new StringBuilder("+212");
        StringBuilder case2 = new StringBuilder("");

        // check if the string already has a "+"
        if (phone.charAt(0) != '+') {
            for (int i = 0; i < phone.length(); i++) {
                // remove any spaces or "-"
                if (phone.charAt(i) != '-' && phone.charAt(i) != ' ') {
                    case1.append(phone.charAt(i));
                }
            }
            return case1.toString();
        } else {
            for (int i = 0; i < phone.length(); i++) {
                // remove any spaces or "-"
                if (phone.charAt(i) != '-' || phone.charAt(i) != ' ') {
                    case2.append(phone.charAt(i));
                }
            }
            return case2.toString();
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
