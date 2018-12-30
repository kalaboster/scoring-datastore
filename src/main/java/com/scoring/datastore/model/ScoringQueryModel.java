package com.scoring.datastore.model;

import java.util.Map;
import java.util.Objects;

public class ScoringQueryModel {

    private Select select;
    private Order order;
    private Filter filter;

    public Select getSelect() {
        return select;
    }

    public void setSelect(Select select) {
        this.select = select;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    static class Select {

        boolean stb;
        boolean title;
        boolean provider;
        boolean date;
        boolean rev;
        boolean viewTime;

        public boolean isStb() {
            return stb;
        }

        public void setStb(boolean stb) {
            this.stb = stb;
        }

        public boolean isTitle() {
            return title;
        }

        public void setTitle(boolean title) {
            this.title = title;
        }

        public boolean isProvider() {
            return provider;
        }

        public void setProvider(boolean provider) {
            this.provider = provider;
        }

        public boolean isDate() {
            return date;
        }

        public void setDate(boolean date) {
            this.date = date;
        }

        public boolean isRev() {
            return rev;
        }

        public void setRev(boolean rev) {
            this.rev = rev;
        }

        public boolean isViewTime() {
            return viewTime;
        }

        public void setViewTime(boolean viewTime) {
            this.viewTime = viewTime;
        }
    }

    static class Order {

        Integer stb;
        Integer title;
        Integer provider;
        Integer date;
        Integer rev;
        Integer viewTime;

        public Integer getStb() {
            return stb;
        }

        public void setStb(Integer stb) {
            this.stb = stb;
        }

        public Integer getTitle() {
            return title;
        }

        public void setTitle(Integer title) {
            this.title = title;
        }

        public Integer getProvider() {
            return provider;
        }

        public void setProvider(Integer provider) {
            this.provider = provider;
        }

        public Integer getDate() {
            return date;
        }

        public void setDate(Integer date) {
            this.date = date;
        }

        public Integer getRev() {
            return rev;
        }

        public void setRev(Integer rev) {
            this.rev = rev;
        }

        public Integer getViewTime() {
            return viewTime;
        }

        public void setViewTime(Integer viewTime) {
            this.viewTime = viewTime;
        }
    }


    static class Filter {

        Map<String, String> map;

        public Map<String, String> getMap() {
            return map;
        }

        public void setMap(Map<String, String> map) {
            this.map = map;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoringQueryModel that = (ScoringQueryModel) o;
        return Objects.equals(select, that.select) &&
                Objects.equals(order, that.order) &&
                Objects.equals(filter, that.filter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(select, order, filter);
    }

    @Override
    public String toString() {
        return "ScoringQueryModel{" +
                "select=" + select +
                ", order=" + order +
                ", filter=" + filter +
                '}';
    }
}
