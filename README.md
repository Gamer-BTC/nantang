# 南塘儿童成长空间 - Spring Boot 网站

## 项目概述

本项目是一个基于Spring Boot开发的展示网站，旨在展示南塘儿童成长空间的内容。页面主要展示以下四个部分：

1. **儿童积分和经验值**
2. **艺术NFT展示**（从CKB区块链获取）
3. **儿童成长记录**（从Nostr协议获取）
4. **记录儿童成长**（存储在Nostr协议或ckb上）

此外，创新提供更轻量级的应用架构，使用 **CKB（Nervos区块链）** 和 **Nostr协议** 代替传统数据库的选项。这使得应用可以实现去中心化的数据存储，适合轻型应用例如个人网站、博客和区块链相关应用场景。

## 可选的存储方式

### 1. **使用 CKB（Nervos区块链）** 作为 NFT 存储
为了简化数据管理和提供去中心化的存储，本项目使用 **CKB（Nervos区块链）** 来存储和管理艺术NFT。这样可以确保NFT数据的安全性和不可篡改性，同时适用于需要区块链特性的应用。

- **艺术NFT展示**：应用从 CKB 区块链中读取 NFT 数据，包括 NFT 的名称和图像链接。这些数据通过 Nervos CKB 网络进行存储和验证，确保其去中心化和安全性。

### 2. **使用 Nostr 协议 和 CKB（Nervos区块链）** 作为儿童成长记录存储
为了进一步增强轻量级架构，应用使用 **Nostr协议** 来存储儿童成长记录。Nostr 是一种轻量级的去中心化协议，它不依赖于传统的数据库，而是通过简单的消息传递和去中心化存储来管理数据。

- **儿童成长记录**：成长记录通过 Nostr 协议存储，这使得每条记录都是去中心化的，具有可验证性，并且数据可以在全球范围内共享。CKB 可保证数据的永久存储。

### 3. **传统数据库（可选）**
如果您希望使用传统的关系型数据库来存储数据，可以根据需要将数据模型（如儿童积分、艺术NFT和成长记录）存储在 MySQL、PostgreSQL 或任何其他数据库中。此功能可以通过简单的数据库配置来启用，具体的实现可根据项目需求选择。

## 数据一致性策略

在本项目中，**Nostr** 和 **CKB** 是两个去中心化的数据存储系统。当 **Nostr** 需要使用 **CKB** 中的数据（如NFT和积分）时，我们采取了以下一致性策略来确保数据同步和一致性：

### 1. **数据同步机制**

- **单向同步**：当 **Nostr** 需要从 **CKB** 获取数据（例如展示NFT和积分）时，系统定期从 **CKB** 更新 **Nostr** 数据。

- **双向同步**：当 **Nostr** 中的数据（如儿童成长记录）更新时，可能需要同步到 **CKB**。更新操作会通过事件驱动或定期任务进行，确保 **CKB** 和 **Nostr** 数据保持一致。

### 2. **定期校验与同步**

系统定期检查 **Nostr** 和 **CKB** 中的数据是否一致。如果发现不一致，系统将触发同步操作，确保两者的数据同步。

### 3. **冲突解决**

如果同步时发生数据冲突（例如时间戳不一致），系统将优先选择最新的数据，并在必要时提供人工干预的方式进行修复。

### 4. **最终一致性**

本项目采用 **最终一致性** 模型，即使某一时刻 **CKB** 和 **Nostr** 的数据不一致，系统将通过定期任务或事件驱动机制最终确保两者的数据一致。

### 5. **同步触发**

- **事件驱动**：当 **Nostr** 更新时，系统会触发同步操作，更新 **CKB**。
- **定时任务**：定期校验和同步 **Nostr** 和 **CKB** 之间的数据，确保它们的一致性。

## 如何运行

1. 克隆项目到本地
2. 确保安装了JDK 22或更高版本
3. 使用Maven构建项目

## 如何运行

1. 克隆项目到本地
2. 确保安装了JDK 22或更高版本
3. 使用Maven构建项目：`mvn clean install`
4. 启动项目：`mvn spring-boot:run`
5. 打开浏览器，访问 `http://localhost:8080` 查看展示页面

## 说明

1. **儿童积分和经验值**：模拟了儿童的积分和经验数据。
2. **艺术NFT展示**：从CKB区块链获取NFT图片和名称。
3. **儿童成长记录**：从Nostr协议获取儿童成长过程中的记录。
4. **记录儿童成长**（存储在Nostr协议或ckb上）

