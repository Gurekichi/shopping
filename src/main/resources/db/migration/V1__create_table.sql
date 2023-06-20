-- テーブルが重複しないよう存在チェック、あれば削除します
DROP TABLE IF EXISTS to_buy_object;

-- 買うものテーブル
CREATE TABLE `to_buy_object` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL,
  `picture_id` VARCHAR(256),
  `delete_flag` INT(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;