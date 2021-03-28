# 計算機アプリ 概要
1. iOS デフォルトの電卓アプリに似せた電卓アプリ Android version.
2. 開発環境: Android Studio 3.6.3
3. 検証端末: Pixel3 (Android 11)


# 使い方
一般的な電卓アプリと同様です。
要件として以下の仕様を満たしています。

<仕様>
- 自然数同士の２項による四則計算ができること
- 入力UI,結果表示UI を含むこと

また、以下の機能を追加しました。
- 符号変換ボタンの追加
- 百分率計算ボタンの追加 (iOSの電卓アプリに倣いました)
- エラー時にトーストを表示する処理

アプリ使用時の制限に以下があります。
- 端末の画面幅から、 9 桁以下の自然数同士の演算が可能になっています。演算結果が 10 桁を超えた場合、オーバフローとして扱い、トーストを表示してお知らせします。
- 0 で除算した場合は、トーストを出力してお知らせします。

# 簡易テスト内容
- ボタンタップで落ちないこと
- 四則演算ができること
- 計算結果が正しく表示されること。見切れないこと。
- 0除算をしたときにクラッシュしないこと。エラーを表示すること
- オーバーフローしたときにクラッシュしないこと。エラーを表示すること