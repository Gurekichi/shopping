<template>
  <!--TODO: (1)最上部のHome, Aboutが表示されないようにする-->
  <div class="outline">
    <label>
      <input type="text" v-model="request.name" />
      <!-- v-on:change でファイルの選択を検知する -->
      <input @change="selectedFile" type="file" name="file" accept="image/*" />
      <!--TODO: レスポンスが返ってきたら画像選択を空欄に戻す-->
      <!--TODO: 画像は1つしか選択できないようにする-->
      <!--TODO: 画像プレビューを実装。JSのライブラリでできそう。-->
      <input type="button" value="追加" @click="addItem" />
    </label>
    <table>
      <tbody v-for="toBuyObject in toBuyObjects" :key="toBuyObject.id">
        <tr>
          <td>
            <!--TODO: (2)ここを削除ボタンと統合して、チェックマークを押すと削除できるようにする-->
            <!--TODO: 削除 = delete_flag1にする-->
            <font-awesome-icon icon="fa-solid fa-circle-check" />
          </td>
          <td>{{ toBuyObject.name }}</td>
          <td>
            <!--TODO: toBuyObject.picture_idと紐付けた画像を表示する-->
            <input
              type="button"
              value="削除"
              @click="deleteItem(toBuyObject.id)" />
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
  export default {
    name: "ShoppingListView",
    data() {
      return {
        request: {
          name: undefined,
          pictureId: undefined,
        },
        toBuyObjects: [],
      };
    },
    created: async function () {
      await this.refresh();
    },

    methods: {
      refresh: function () {
        fetch("/shopping-list", { method: "GET" })
          .then((response) => response.json())
          .then((data) => {
            this.toBuyObjects = data.toBuyObjects;
            this.request.name = undefined;
            this.request.pictureId = undefined;
          });
      },

      selectedFile: function (e) {
        // 選択された File の情報を保存しておく
        //TODO: 一時PATHの生成を行い、バックエンドに渡せるようにする
        e.preventDefault();
        let files = e.target.files;
        this.$data.request.pictureId = files[0];
      },

      addItem: async function () {
        //TODO: nameの空判定をバックエンドに移す
        //空判定→画像があれば画像IDを一意に再生成→S3アップロード→画像IDをDB登録→レスポンス
        if (this.$data.request.name) {
          const formData = new FormData();
          formData.append("name", this.$data.request.name);
          formData.append("file", this.$data.request.pictureId);
          await fetch("/shopping-list", {
            method: "POST",
            /*headers: {
              "Content-Type": "multipart/form-data",
            },*/
            //body: JSON.stringify(this.$data.request),
            body: formData,
          });
        }
        await this.refresh();
      },
      deleteItem: async function (id) {
        await fetch("/shopping-list/" + id, { method: "DELETE" });
        await this.refresh();
      },
    },
  };
</script>

<style lang="less">
  @import "../../resource/assets/less/shoppingList";
</style>
