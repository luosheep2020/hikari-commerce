[README.md](https://github.com/user-attachments/files/33104134/README.md)
# Hikari Commerce

[English](#english) | [日本語](#日本語)

## English

A work-in-progress e-commerce backend built with Java, Spring Boot, MyBatis-Plus, and MySQL. This project focuses on implementing business rules with a clear layered structure.

## Technology Stack

- Java 21
- Spring Boot
- MyBatis-Plus
- MySQL
- Jakarta Bean Validation
- Lombok

## Development Progress

- Brand management: CRUD and pagination
- Category management: tree queries, creation, and updates with parent validation
- Product (SPU) management: creation and updates; pagination in progress
- Common infrastructure: unified responses, exception handling, request validation, pagination models, and automatic timestamps

The project is under active development. Some endpoints and validation rules are still being implemented and tested.

## Project Structure

```text
com.hikaricommerce.mall
├── common
│   ├── config
│   ├── dto
│   ├── entity
│   ├── enums
│   ├── exception
│   ├── filter
│   ├── handler
│   └── result
└── product
    ├── controller
    ├── dto
    ├── entity
    ├── enums
    ├── mapper
    ├── repository
    │   └── impl
    ├── service
    │   └── impl
    └── vo
```

Controllers handle HTTP requests, services implement business rules, repositories provide data access operations, and mappers execute database operations.

- **DTO**: request parameters and validation rules
- **Entity**: database table mapping
- **VO**: API response data

## Local Setup

1. Install Java 21 and prepare a MySQL database.
2. Import the project's database schema.
3. Configure the datasource URL and username in `application.yml`.
4. Set the `DB_PASSWORD` environment variable referenced by the datasource configuration.
5. Run `HikariCommerceApplication` in IntelliJ IDEA, or use the Maven wrapper:

```bash
./mvnw spring-boot:run
```

When using a `.env` file in IntelliJ IDEA, configure the run configuration to load it. Spring Boot does not automatically load `.env` files by default. Keep credentials out of Git.

## API Conventions

Administrative APIs use the `/api/admin` prefix. Current resource paths include:

- `/api/admin/brand`
- `/api/admin/category`
- `/api/admin/spu`

Typical operations use `GET` for queries, `POST` for creation, `PUT /{id}` for full edits, and `DELETE /{id}` for deletion. Endpoint availability depends on implementation progress.

Responses use a common format:

```json
{
  "code": 0,
  "message": "Success",
  "data": null
}
```

`code = 0` indicates success. Errors use application error codes and appropriate HTTP status codes. The example message is illustrative.

Pagination accepts `pageNum` and `pageSize` and returns `pageNum`, `pageSize`, `total`, and `records`.

## Product Model

- **SPU** (`pms_spu`): product information, brand, category, and display content
- **SKU** (`pms_sku`): a specific product variant with price and inventory

SKU prices are stored as integer amounts in cents. SPU prices should follow the same convention after verifying existing data.

## Roadmap

- Complete and verify SPU pagination
- Implement product publishing and unpublishing
- Implement safe product deletion
- Implement SKU management and inventory adjustments
- Add authentication and authorization
- Add order management
- Expand automated tests

## Project Status

This is a learning project under active development and is not ready for production use.

---

## 日本語

Java、Spring Boot、MyBatis-Plus、MySQL を使用した、開発中の EC バックエンドです。明確なレイヤー構成で、業務ルールを実装することを目指しています。

### 技術スタック

- Java 21
- Spring Boot
- MyBatis-Plus
- MySQL
- Jakarta Bean Validation
- Lombok

### 開発状況

- ブランド管理：CRUD、ページネーション
- カテゴリ管理：ツリー取得、作成、更新、親カテゴリの検証
- 商品（SPU）管理：作成、更新。ページネーションは実装中
- 共通機能：統一レスポンス、例外処理、入力検証、ページネーション用モデル、作成・更新日時の自動設定

現在も開発を進めています。一部の API と検証処理は実装・テスト中です。

### プロジェクト構成

パッケージ構成は上記の [Project Structure](#project-structure) を参照してください。

Controller は HTTP リクエストを処理し、Service は業務ルールを実装します。Repository はデータアクセス操作を提供し、Mapper はデータベース操作を実行します。

- **DTO**：リクエストパラメータと入力検証ルール
- **Entity**：データベーステーブルとのマッピング
- **VO**：API のレスポンスデータ

### ローカル環境での起動

1. Java 21 をインストールし、MySQL データベースを用意します。
2. プロジェクトのデータベーススキーマをインポートします。
3. `application.yml` にデータソースの URL とユーザー名を設定します。
4. データソース設定が参照する環境変数 `DB_PASSWORD` を設定します。
5. IntelliJ IDEA で `HikariCommerceApplication` を実行するか、Maven Wrapper で起動します。

```bash
./mvnw spring-boot:run
```

IntelliJ IDEA で `.env` ファイルを使用する場合は、実行構成で読み込むように設定してください。Spring Boot は標準では `.env` ファイルを自動で読み込みません。認証情報は Git にコミットしないでください。

### API の規約

管理用 API のプレフィックスは `/api/admin` です。現在のリソースパスは次のとおりです。

- `/api/admin/brand`
- `/api/admin/category`
- `/api/admin/spu`

基本的に、取得には `GET`、作成には `POST`、編集可能な項目の一括更新には `PUT /{id}`、削除には `DELETE /{id}` を使用します。各エンドポイントの利用可否は実装状況によって異なります。

レスポンスは共通形式で返します。

```json
{
  "code": 0,
  "message": "Success",
  "data": null
}
```

`code = 0` は成功を表します。エラー時はアプリケーション固有のエラーコードと、適切な HTTP ステータスコードを返します。上記のメッセージは例です。

ページネーションでは `pageNum` と `pageSize` を受け取り、`pageNum`、`pageSize`、`total`、`records` を返します。

### 商品モデル

- **SPU**（`pms_spu`）：商品情報、ブランド、カテゴリ、表示用コンテンツ
- **SKU**（`pms_sku`）：価格と在庫を持つ、具体的な商品バリエーション

SKU の価格は人民元の補助単位「分」（1 元 = 100 分）の整数値で保存します。SPU の価格も、既存データの単位を確認したうえで同じ方式に統一する予定です。

### 今後の予定

- SPU のページネーションの実装・検証
- 商品の販売開始・停止機能
- 関連データを確認する商品削除処理
- SKU 管理と在庫調整
- 認証・認可
- 注文管理
- 自動テストの拡充

### プロジェクトの位置づけ

学習を目的として開発しているプロジェクトです。現時点では本番運用を想定した完成状態ではありません。
