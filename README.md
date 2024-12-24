# 制作1

## サービス名
家電おすすめアプリ

## アプリ・サービスの概要
掃除機や電子レンジなどの家電製品を新規で購入する際に役立つアプリです。  
ユーザーが各家電カテゴリーに分かれた選択肢から項目を選ぶと、おすすめの製品が表示される仕様になっています。  
診断結果が簡単にわかるため、接客時のサポートツールとしても使用予定です。

## 開発背景
前職で接客対応時にアプリの登録案内を行っていましたが、使いにくさを感じることが多く、自ら新しいアプリを開発することを決めました。  
また、接客の待ち時間などにも手軽に利用できるアプリがあれば、サービス向上につながると考えたことも、開発に至った理由の一つです。

## 開発における言語・ツール
- HTML
- CSS
- サーブレットJSP
- Canva

## アプリ使用ターゲット層
新生活を始める10代から20代の若年層をメインターゲットとしています。

## アプリ作成時にこだわったポイント
- **分かりやすく、親しみやすいデザイン**  
  ユーザーが直感的に操作できるデザインを心がけました。特に「簡単に診断ができる」という点を最大の特徴とし、操作性の向上に力を入れています。

- **フォームボタンを活用した診断方法**  
  職業訓練で学んだサーブレットやJSPの技術を活用し、診断機能を実装しました。

- **質問内容の簡素化**  
  ユーザーが短時間で診断を完了できるよう、質問項目を簡潔にまとめ、ユーザビリティを高める工夫を施しました。

![アプリのスクリーンショット](/images/github1.png)

![アプリのスクリーンショット](/images/github2.png)

## 診断チャート図(一例)
サーブレットとJSPを使用し、ユーザーが選択した回答に応じて結果を分岐させる仕組みを導入しています。一例として掃除機のチャート図を記載しています。

![掃除機のチャート図](/images/shindanchart.png)

## 今後の改善点
診断後に売り出し商品のURLページに遷移できる機能を追加することで、ユーザーの購入機会の拡大につながると考えています。  
販促に繋げる事が出来るアプリへと展開していきたいです。  
また、診断できる家電の種類や、診断結果の分岐なども今後増やしていきたいと考えています。

# 制作2

## サービス名
ITパスポート試験攻略ゲーム

## アプリ・サービスの概要
ITパスポート試験の学習を楽しく行える選択式問題ゲームです。ゲーム内では味方キャラと敵キャラがそれぞれHP（ライフ）を持ち、問題を解き進めるごとに攻撃を与える事ができます。    
もし、問題を間違えると攻撃を受けて味方HPは減り、問題に正解すると攻撃を与えて敵HPが減ります。  
味方のHPが敵のHPを下回るとゲームオーバー、味方のHPが敵のHPを上回るとゲームクリアになります。

## 開発背景
株式会社ジョブリッチの就労移行支援事業所では、職業訓練校を通じてパソコンスキルの習得が可能であり、利用者の方のスキルアップをサポートできる点が強みとなっています。私はこの強みを活かし、利用者の方のスキルアップにつながる制作物を開発したいと考えました。  
パソコンスキルを学ぶ中で、IT業界への興味を持つ方も多いのではないかと感じています。また、ITリテラシーの向上という観点からも「ITパスポート試験」の需要が増加していると考えられます。  
私自身、この試験に挑戦して資格を取得しましたが、その勉強過程で感じた課題を解決したいという思いから、本アプリの開発を決定しました。

## 開発における言語・ツール
- HTML
- CSS
- サーブレットJSP
- Java
- Canva
- dotpict
- H2データベース

## アプリ使用ターゲット層
株式会社ジョブリッチの就労移行支援事業所の利用者の方と、ITパスポート試験合格を目指している方をメインターゲットとしています。

## アプリ作成時にこだわったポイント
- **学びながら楽しめるというゲーム性**  
  テキスト量が多く、暗記が負担になりがちな試験学習を楽しい体験へと変えることを目指しました。

- **データベースの使用**  
  問題やユーザー情報をデータベースで管理することで、多様な問題を提供できるようにしました。これにより、家電診断アプリでは対応できなかった規模のデータ処理が可能となりました。

- **ドット絵を使用したデザイン**  
  ゲーム内のキャラクターデザインには、dotpictというアプリケーションを活用し、オリジナルのドット絵を制作しました。これにより、ユーザーに親しみやすいビジュアルを提供しています。

![ゲーム画面のスクリーンショット](/images/github3.png)

## 今後の改善点
今回はページ遷移が少ない形で設計を行いましたが、ユーザビリティの観点から正解のみを表示するjspファイルを作成するべきではないかと考えています。今後、QuestionResult.jspを作成し、ページ遷移する形に改善していきたいです。  
また、データベース設計を拡張し様々な問題形式に対応可能なシステムへの変更や、ユーザーのゲーム履歴が見れるようにするなど改善していきたいです。

## 実行環境

1. **データベースファイルの配置**

   デスクトップ上に制作SQLのフォルダを作成し、以下のデータベースファイルを配置してください:
   - `user.mv.db`
   - `user.trace.db`

2. **プロジェクトの実行**

   データベースファイルの配置が完了したら、プロジェクトを実行してください。（index.jspもしくはプロジェクトから開始）

3. **ログイン方法**

   以下のいずれかの方法でゲームを開始できます:
   
   1. **既存ユーザーでログイン**:
      - ユーザー名: `test`
      - パスワード: `1234`
   
   2. **新規登録**:
      - 新しいユーザーを登録後、ゲームを開始してください。
