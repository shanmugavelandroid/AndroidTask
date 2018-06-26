package shanmugaveltask_project.com.sampletask.model.response;

import java.util.List;

public class UserMaster {


    /**
     * page : 1
     * per_page : 3
     * total : 12
     * total_pages : 4
     * data : [{"id":1,"first_name":"George","last_name":"Bluth","avatar":"https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg"},{"id":2,"first_name":"Janet","last_name":"Weaver","avatar":"https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg"},{"id":3,"first_name":"Emma","last_name":"Wong","avatar":"https://s3.amazonaws.com/uifaces/faces/twitter/olegpogodaev/128.jpg"}]
     */

    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<UserDetails> data;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<UserDetails> getData() {
        return data;
    }

    public void setData(List<UserDetails> data) {
        this.data = data;
    }


   /* public static class UserDetails {
        *//**
         * id : 1
         * first_name : George
         * last_name : Bluth
         * avatar : https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg
         *//*


        private int id;
        private String first_name;
        private String last_name;
        private String avatar;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }*/
}
