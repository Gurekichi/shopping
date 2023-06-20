package com.group.shopping.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ToBuyObject {

        /** 自動採番ID */
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        /** 名称 */
        private String name;

        /** 写真ID */
        private String picture_id;

        /** 論理削除フラグ */
        private int delete_flag;

        public static ToBuyObject newToBuyObject(String name) {
        	ToBuyObject toBuyObject = new ToBuyObject();
        	toBuyObject.setName(name);
        	return toBuyObject;
        }
}
