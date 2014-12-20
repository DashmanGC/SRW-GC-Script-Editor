/*
 * Copyright (C) 2014 Dashman
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package srwgcscripteditor;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonatan
 */
public class Dialogue {
        byte[] opcodes; // Opcodes before the dialogue
        String speaker; // Name of character talking
        String original;    // Dialogue read from the file
        String common;  // Dialogue common to both sexes
                        // if char_sex isn't 0, this is the text that is used
        String male;    // Dialogue for male route
                        // only used if we choose to make sex versions of the same dialogue
        String female;  // Dialogue for female route
                        // same as above
        int char_sex;   // Indicates which sex this dialogue is used for
                        // 0 - common (both), 1 - male, 2 - female
        int robot_type; // Indicates which robot type this dialogue is used for
                        // 0 - common (both), 1 - super, 2 - real
        boolean voiced; // Indicates if the line is voiced or not. Adds 2 bytes to the opcode
        boolean remote; // Indicaes if the line is remote or not. Adds 2 bytes to the opcode
        
        String font_encoding = "MS932";
        
        public Dialogue(){
            opcodes = null;
            speaker = "";
            original = "";
            common = "";
            male = "";
            female = "";
            char_sex = 0;
            robot_type = 0;
            voiced = false;
            remote = false;
        }

        public Dialogue(byte[] op, String o, int sex, int type, boolean v, boolean r){
            opcodes = op;
            original = o;
            speaker = findName();
            male = "";
            female = "";
            char_sex = sex;
            robot_type = type;
            voiced = v;
            remote = r;

            // Add two empty spaces at the beginning of the speaker name if it doesn't start with an empty space
            //if (!speaker.startsWith("　"))
            //    speaker = "　　" + speaker;

            String[] lines = o.split("\n");
            String c = "";
            for (int i = 1; i < lines.length; i++){
                // Add two empty SJIS spaces to the beginning of each line if it doesn't start with two empty spaces
                if (!lines[i].startsWith("　　")){
                    if (lines[i].startsWith("　"))   // If it starts with ONE space, we add another
                        c += "　";
                    else
                        c += "　　";
                }

                c += lines[i];
                if (i != lines.length - 1)
                    c += "\n";
            }

            common = c;
        }

        private String findName(){
            String[] lines = original.split("\n");
            String name = lines[0];

            // Don't compare if it's already translated!
            char first = name.charAt(0);

            if (first < 'ぁ')
                return name;

            // Compare against all names in the database and give the according one
            if (name.equals("忍"))
                return "Ｓｈｉｎｏｂｕ";
            else if (name.equals("沙羅"))
                return "Ｓａｒａ";
            else if (name.equals("亮"))
                return "Ｒｙｏｕ";
            else if (name.equals("雅人"))
                return "Ｍａｓａｔｏ";
            else if (name.equals("アラン"))
                return "Ａｌａｎ";
            else if (name.equals("葉月博士"))
                return "Ｐｒｏｆ．　Ｈａｚｕｋｉ";
            else if (name.equals("イゴール長官"))
                return "Ｃｈｉｅｆ　Ｉｇｏｒ";
            else if (name.equals("シャピロ"))
                return "Ｓｈａｐｉｒｏ";
            else if (name.equals("デスガイヤー"))
                return "Ｄｅａｔｈｇａｉａ";
            else if (name.equals("ギルドローム"))
                return "Ｇｉｌｄｏｒｏｍｅ";
            else if (name.equals("ヘルマット"))
                return "Ｈｅｌｍｕｔ";
            else if (name.equals("ムゲ"))
                return "Ｍｕｇｅ";

            else if (name.equals("キッド"))
                return "Ｋｉｄ";
            else if (name.equals("ボウィー"))
                return "Ｂｏｗｉｅ";
            else if (name.equals("お町"))
                return "Ｏｍａｃｈｉ";
            else if (name.equals("アイザック"))
                return "Ｉｓａａｃ";
            else if (name.equals("ポンチョ"))
                return "Ｐｏｎｃｈｏ";
            else if (name.equals("カーメン"))
                return "Ｋｈａｍｅｎ";
            else if (name.equals("コブラ"))
                return "Ｃｏｂｒａ";

            else if (name.equals("ディーゴ"))
                return "Ｄｉｅｇｏ";
            else if (name.equals("シュテッケン"))
                return "Ｓｔｅｃｋｅｎ";
            else if (name.equals("士郎"))
                return "Ｓｈｉｒｏｕ";
            else if (name.equals("ライラ"))
                return "Ｌａｙｌａ";
            else if (name.equals("佐馬"))
                return "Ｓａｍａ";
            else if (name.equals("スリーＪ"))
                return "Ｔｈｒｅｅ　Ｊ";
            else if (name.equals("オズマ"))
                return "Ｏｚｍａ";
            else if (name.equals("ナーカ"))
                return "Ｎａｒｋａ";
            else if (name.equals("ケイ"))
                return "Ｋｅｙ";
            else if (name.equals("イーゴ"))
                return "Ｉｇｏ";

            else if (name.equals("ブルース"))
                return "Ｂｌｕｅｓ";
            else if (name.equals("ロック"))
                return "Ｒｏｃｋ";
            else if (name.equals("ビート"))
                return "Ｂｅａｔ";
            else if (name.equals("バーディ"))
                return "Ｂｉｒｄｙ";
            else if (name.equals("ブラディ"))
                return "Ｂｌｏｏｄｙ";

            else if (name.equals("アムロ"))
                return "Ａｍｕｒｏ";
            else if (name.equals("セイラ"))
                return "Ｓａｙｌａ";
            else if (name.equals("カイ"))
                return "Ｋａｉ";
            else if (name.equals("ハヤト"))
                return "Ｈａｙａｔｏ";
            else if (name.equals("リュウ"))
                return "Ｒｙｕ";
            else if (name.equals("スレッガー"))
                return "Ｓｌｅｇｇａｒ";
            else if (name.equals("マチルダ"))
                return "Ｍａｔｉｌｄａ";
            else if (name.equals("ブライト"))
                return "Ｂｒｉｇｈｔ";
            else if (name.equals("リード"))
                return "Ｒｅｅｄ";
            else if (name.equals("ミライ"))
                return "Ｍｉｒａｉ";
            else if (name.equals("フラウ"))
                return "Ｆｒａｗ";
            else if (name.equals("ワッケイン"))
                return "Ｗａｔｋｅｉｎ";
            else if (name.equals("シャア"))
                return "Ｃｈａｒ";
            else if (name.equals("ララァ"))
                return "Ｌａｌａｈ";
            else if (name.equals("デニム"))
                return "Ｄｅｎｉｍ";
            else if (name.equals("ジーン"))
                return "Ｇｅｎｅ";
            else if (name.equals("スレンダー"))
                return "Ｓｌｅｎｄｅｒ";
            else if (name.equals("クラウン"))
                return "Ｃｒｏｗｎ";
            else if (name.equals("ラル"))
                return "Ｒａｌ";
            else if (name.equals("アコース"))
                return "Ａｃｏｕｓ";
            else if (name.equals("コズン"))
                return "Ｃｏｚｕｎ";
            else if (name.equals("ガイア"))
                return "Ｇａｉａ";
            else if (name.equals("オルテガ"))
                return "Ｏｒｔｅｇａ";
            else if (name.equals("マッシュ"))
                return "Ｍａｓｈ";
            else if (name.equals("ドズル"))
                return "Ｄｏｚｌｅ";
            else if (name.equals("ガルマ"))
                return "Ｇａｒｍａ";
            else if (name.equals("ギレン"))
                return "Ｇｉｈｒｅｎ";
            else if (name.equals("キシリア"))
                return "Ｋｙｃｉｌｉａ";
            else if (name.equals("ハモン"))
                return "Ｈａｍｏｎ";
            else if (name.equals("クランプ"))
                return "Ｃｌａｍｐ";
            else if (name.equals("コンスコン"))
                return "Ｃｏｎｓｃｏｎ";
            else if (name.equals("マ・クベ"))
                return "Ｍ´Ｑｕｖｅ";
            else if (name.equals("ドレン"))
                return "Ｄｒｅｎ";
            else if (name.equals("デギン"))
                return "Ｄｅｇｗｉｎ";
            else if (name.equals("イセリナ"))
                return "Ｉｃｅｌｉｎａ";
            else if (name.equals("レビル"))
                return "Ｒｅｖｉｌ";
            else if (name.equals("オスカ"))
                return "Ｏｓｃａｒ";
            else if (name.equals("マーカー"))
                return "Ｍａｒｋｅｒ";
            else if (name.equals("タチ"))
                return "Ｔａｃｈｉ";
            else if (name.equals("トクワン"))
                return "Ｔｏｋｗａｎ";

            else if (name.equals("バーニィ"))
                return "Ｂｅｒｎｉｅ";
            else if (name.equals("クリス"))
                return "Ｃｈｒｉｓ";
            else if (name.equals("アル"))
                return "Ａｌ";
            else if (name.equals("シュタイナー"))
                return "Ｓｔｅｉｎｅｒ";
            else if (name.equals("ミーシャ"))
                return "Ｍｉｓｈａ";
            else if (name.equals("ガルシア"))
                return "Ｇａｒｃｉａ";
            else if (name.equals("アンディ"))
                return "Ａｎｄｙ";


            else if (name.equals("シロー"))
                return "Ｓｈｉｒｏ";
            else if (name.equals("サンダース"))
                return "Ｓａｎｄｅｒｓ";
            else if (name.equals("カレン"))
                return "Ｋａｒｅｎ";
            else if (name.equals("ミケル"))
                return "Ｍｉｃｈｅｌ";
            else if (name.equals("エレドア"))
                return "Ｅｌｅｄｏｒｅ";
            else if (name.equals("キキ"))
                return "Ｋｉｋｉ";
            else if (name.equals("バレスト"))
                return "Ｂａｒｅｓｔ";
            else if (name.equals("アイナ"))
                return "Ａｉｎａ";
            else if (name.equals("ノリス"))
                return "Ｎｏｒｒｉｓ";
            else if (name.equals("ギニアス"))
                return "Ｇｉｎｉａｓ";


            else if (name.equals("カミーユ"))
                return "Ｋａｍｉｌｌｅ";
            else if (name.equals("ファ"))
                return "Ｆａ";
            else if (name.equals("クワトロ"))
                return "Ｑｕａｔｔｒｏ";
            else if (name.equals("エマ"))
                return "Ｅｍｍａ";
            else if (name.equals("ハマーン"))
                return "Ｈａｍａｎ";
            else if (name.equals("フォウ"))
                return "Ｆｏｕｒ";

            else if (name.equals("ジュドー"))
                return "Ｊｕｄａｕ";
            else if (name.equals("ルー"))
                return "Ｒｏｕｘ";
            else if (name.equals("エル"))
                return "Ｅｌｌｅ";
            else if (name.equals("ビーチャ"))
                return "Ｂｅｅｃｈａ";
            else if (name.equals("モンド"))
                return "Ｍｏｎｄｏ";
            else if (name.equals("イーノ"))
                return "Ｉｉｎｏ";
            else if (name.equals("リィナ"))
                return "Ｌｅｉｎａ";
            else if (name.equals("プル"))
                return "Ｐｕｒｕ";
            else if (name.equals("プルツー"))
                return "Ｐｕｒｕ　Ｔｗｏ";
            else if (name.equals("ロンメル"))
                return "Ｒｏｍｍｅｌ";
            else if (name.equals("ラカン"))
                return "Ｒａｋａｎ";

            else if (name.equals("ダバ"))
                return "Ｄａｂａ";
            else if (name.equals("リリス"))
                return "Ｌｉｌｉｔｈ";
            else if (name.equals("キャオ"))
                return "Ｋｙａｏ";
            else if (name.equals("アム"))
                return "Ａｍｕ";
            else if (name.equals("レッシィ"))
                return "Ｌｅｃｃｅｅ";
            else if (name.equals("セムージュ"))
                return "Ｓｅｍｕｊ";
            else if (name.equals("ステラ"))
                return "Ｓｔｅｌｌａ";
            else if (name.equals("ギャブレー"))
                return "Ｇａｂｌａｅ";
            else if (name.equals("ギワザ"))
                return "Ｇｉｗａｚａ";
            else if (name.equals("ネイ"))
                return "Ｎｅｉ";
            else if (name.equals("マクトミン"))
                return "ＭｃＴｏｍｉｎ";
            else if (name.equals("チャイ・チャー"))
                return "Ｃｈａｉ　Ｃｈａｒ";
            else if (name.equals("クワサン"))
                return "Ｑｕｗａｓａｎ";
            else if (name.equals("リョクレイ"))
                return "Ｒｙｏｋｌａｙ";
            else if (name.equals("アマンダラ"))
                return "Ａｍａｎｄａｒａ";
            else if (name.equals("ポセイダル"))
                return "Ｐｏｓｅｉｄａｌ";
            else if (name.equals("アントン"))
                return "Ａｎｔｏｎ";
            else if (name.equals("ヘッケラー"))
                return "Ｈｅｃｋｌｅｒ";
            else if (name.equals("ハッシャ"))
                return "Ｈａｓｈａ";
            else if (name.equals("フラット"))
                return "Ｆｌａｔ";

            else if (name.equals("エイジ"))
                return "Ｅｉｊｉ";
            else if (name.equals("デビッド"))
                return "Ｄａｖｉｄ";
            else if (name.equals("ロアン"))
                return "Ｒｏｗａｎ";
            else if (name.equals("シモーヌ"))
                return "Ｓｉｍｏｎｅ";
            else if (name.equals("アーサー"))
                return "Ａｒｔｈｕｒ";
            else if (name.equals("エリザベス"))
                return "Ｅｌｉｚａｂｅｔｈ";
            else if (name.equals("レイ"))
                return "Ｒｅｉ";
            else if (name.equals("アンナ"))
                return "Ａｎｎａ";
            else if (name.equals("ジュリア"))
                return "Ｊｕｌｉａ";
            else if (name.equals("ル・カイン"))
                return "Ｌｅ　Ｃａｉｎｅ";
            else if (name.equals("ゴステロ"))
                return "Ｇｏｓｔｅｒｒｏ";
            else if (name.equals("マンジェロ"))
                return "Ｍａｎｊｅｒｏ";
            else if (name.equals("ボーン"))
                return "Ｂｏｈｎ";
            else if (name.equals("ゲティ"))
                return "Ｇｅｔｙ";
            else if (name.equals("グレスコ"))
                return "Ｇｒｅｓｃｏ";
            else if (name.equals("ゲイル"))
                return "Ｇａｌｅ";

            else if (name.equals("ケーン"))
                return "Ｋａｉｎｅ";
            else if (name.equals("タップ"))
                return "Ｔａｐｐ";
            else if (name.equals("ライト"))
                return "Ｌｉｇｈｔ";
            else if (name.equals("リンダ"))
                return "Ｌｉｎｄａ";
            else if (name.equals("プラート博士"))
                return "Ｐｒｏｆ．　Ｐｌａｔｏ";
            else if (name.equals("ローズ"))
                return "Ｒｏｓｅ";
            else if (name.equals("ダイアン"))
                return "Ｄｉａｎｅ";
            else if (name.equals("ベン"))
                return "Ｂｅｎ";
            else if (name.equals("マイヨ"))
                return "Ｍｅｉｏ";
            else if (name.equals("ダン"))
                return "Ｄａｎ";
            else if (name.equals("カール"))
                return "Ｋａｒｌ";
            else if (name.equals("ウェルナー"))
                return "Ｗｅｌｎｅｒ";
            else if (name.equals("ミン"))
                return "Ｍｉｎ";
            else if (name.equals("グン・ジェム"))
                return "Ｇｕｎ　Ｊｅｍ";
            else if (name.equals("ゴル"))
                return "Ｇｏｌ";
            else if (name.equals("ガナン"))
                return "Ｇａｎａｎ";
            else if (name.equals("ジン"))
                return "Ｊｉｎ";
            else if (name.equals("ドルチェノフ"))
                return "Ｔｕｒｃｈｉｎｏｖ";
            else if (name.equals("Ｄｏｒｃｈｅｎｏｖ"))
                return "Ｔｕｒｃｈｉｎｏｖ";
            else if (name.equals("ギルトール"))
                return "Ｇｕｉｌｔｏｒｒｅ";

            else if (name.equals("ワッ太"))
                return "Ｗａｔｔａ";
            else if (name.equals("柿小路"))
                return "Ｋａｋｉｋｏｕｊｉ";
            else if (name.equals("郁絵"))
                return "Ｉｋｕｅ";
            else if (name.equals("厚井"))
                return "Ａｔｓｕｉ";
            else if (name.equals("木下"))
                return "Ｋｉｎｏｓｈｉｔａ";
            else if (name.equals("かおる"))
                return "Ｋａｏｒｕ";
            else if (name.equals("足立長官"))
                return "Ｓｅｃｒｅｔａｒｙ　Ａｄａｃｈｉ";
            else if (name.equals("ザクロン"))
                return "Ｚａｋｒｏｎ";
            else if (name.equals("オンドロン"))
                return "Ｏｎｄｏｒｏｎ";
            else if (name.equals("ジルバ"))
                return "Ｊｉｌｖａ";

            else if (name.equals("ミト"))
                return "Ｍｉｔｏ";
            else if (name.equals("スケード"))
                return "Ｓｋａｄｅ";
            else if (name.equals("カークス"))
                return "Ｋｉｒｋｓ";
            else if (name.equals("シノブ"))
                return "Ｓｈｉｎｏｂｕ";
            else if (name.equals("バルジャン"))
                return "Ｂａｌｊａｎ";
            else if (name.equals("トクガー王"))
                return "Ｋｉｎｇ　Ｔｏｋｕｇａ";
            else if (name.equals("トクガー王妃"))
                return "Ｑｕｅｅｎ　Ｔｏｋｕｇａ";
            else if (name.equals("デスバン"))
                return "Ｄｅｓｂａｎ";
            else if (name.equals("バードランド領主"))
                return "Ｂａｒｄｌａｎｄ　Ｌｏｒｄ";
            else if (name.equals("ルビタイ"))
                return "Ｒｕｂｉｔａｉ";
            else if (name.equals("アニタ"))
                return "Ａｎｉｔａ";

            else if (name.equals("仁"))
                return "Ｊｉｎ";
            else if (name.equals("飛鳥"))
                return "Ａｓｕｋａ";
            else if (name.equals("吼児"))
                return "Ｋｏｕｊｉ";
            else if (name.equals("バクリュウオー"))
                return "Ｂａｋｕｒｙｕｕ−Ｏｈ";
            else if (name.equals("バクリュウドラゴン"))
                return "Ｂａｋｕｒｙｕｕ　Ｄｒａｇｏｎ";
            else if (name.equals("マリア"))
                return "Ｍａｒｉａ";
            else if (name.equals("ひろし"))
                return "Ｈｉｒｏｓｈｉ";
            else if (name.equals("勉"))
                return "Ｔｓｕｔｏｍｕ";
            else if (name.equals("クッキー"))
                return "Ｃｏｏｋｉｅ";
            else if (name.equals("ゆう"))
                return "Ｙｕｕ";
            else if (name.equals("ひでのり"))
                return "Ｈｉｄｅｎｏｒｉ";
            else if (name.equals("ラブ"))
                return "Ｌｏｖｅ";
            else if (name.equals("あきら"))
                return "Ａｋｉｒａ";
            else if (name.equals("大介"))
                return "Ｄａｉｓｕｋｅ";
            else if (name.equals("ヨッパー"))
                return "Ｙｏｐｐａ";
            else if (name.equals("美紀"))
                return "Ｍｉｋｉ";
            else if (name.equals("れいこ"))
                return "Ｒｅｉｋｏ";
            else if (name.equals("ときえ"))
                return "Ｔｏｋｉｅ";
            else if (name.equals("きらら"))
                return "Ｋｉｒａｒａ";
            else if (name.equals("ポテト"))
                return "Ｐｏｔａｔｏ";
            else if (name.equals("篠田先生"))
                return "Ｍｒ．　Ｓｈｉｎｏｄａ";
            else if (name.equals("姫木先生"))
                return "Ｍｉｓｓ　Ｈｉｍｅｋｉ";
            else if (name.equals("エルドラン"))
                return "Ｅｌｄｏｒａｎ";
            else if (name.equals("武田長官"))
                return "Ｃｈｉｅｆ　Ｔａｋｅｄａ";
            else if (name.equals("ワルーサ"))
                return "Ｗａｒｕｓａ";
            else if (name.equals("ベルゼブ"))
                return "Ｂｅｌｚｅｂ";
            else if (name.equals("ファルゼブ"))
                return "Ｆａｌｚｅｂ";
            else if (name.equals("タイダー"))
                return "Ｔａｉｄａ";

            else if (name.equals("號"))
                return "Ｇｏｕ";
            else if (name.equals("翔"))
                return "Ｓｈｏｕ";
            else if (name.equals("剴"))
                return "Ｇａｉ";
            else if (name.equals("竜馬"))
                return "Ｒｙｏｕｍａ";
            else if (name.equals("隼人"))
                return "Ｈａｙａｔｏ";
            else if (name.equals("武蔵"))
                return "Ｍｕｓａｓｈｉ";
            else if (name.equals("ジャック"))
                return "Ｊａｃｋ";
            else if (name.equals("メリー"))
                return "Ｍａｒｙ";
            else if (name.equals("早乙女博士"))
                return "Ｐｒｏｆ．　Ｓａｏｔｏｍｅ";
            else if (name.equals("敷島博士"))
                return "Ｐｒｏｆ．　Ｓｈｉｋｉｓｈｉｍａ";
            else if (name.equals("ゴール"))
                return "Ｇｏｒｅ";
            else if (name.equals("バット"))
                return "Ｂａｔ";
            else if (name.equals("ガリレイ"))
                return "Ｇａｌｉｌｅｉ";
            else if (name.equals("ニオン"))
                return "Ｎｉｏｎ";

            else if (name.equals("甲児"))
                return "Ｋｏｕｊｉ";
            else if (name.equals("鉄也"))
                return "Ｔｅｔｓｕｙａ";
            else if (name.equals("さやか"))
                return "Ｓａｙａｋａ";
            else if (name.equals("ボス"))
                return "Ｂｏｓｓ";
            else if (name.equals("ヌケ"))
                return "Ｎｕｋｅ";
            else if (name.equals("ムチャ"))
                return "Ｍｕｃｈａ";
            else if (name.equals("ジュン"))
                return "Ｊｕｎ";
            else if (name.equals("弓教授"))
                return "Ｐｒｏｆ．　Ｙｕｍｉ";
            else if (name.equals("ヘル"))
                return "Ｈｅｌｌ";
            else if (name.equals("あしゅら男爵"))
                return "Ｂａｒｏｎ　Ａｓｈｕｒａ";

            else if (name.equals("剣人"))
                return "Ｋｅｎｔｏ";
            else if (name.equals("弾児"))
                return "Ｄａｎｊｉ";
            else if (name.equals("ベラリオス"))
                return "Ｂｅｒａｌｉｏｓ";
            else if (name.equals("早苗"))
                return "Ｓａｎａｅ";
            else if (name.equals("次郎"))
                return "Ｊｉｒｏｕ";
            else if (name.equals("トン助"))
                return "Ｔｏｎｓｕｋｅ";
            else if (name.equals("田之助"))
                return "Ｔａｎｏｓｕｋｅ";
            else if (name.equals("まなぶ"))
                return "Ｍａｎａｂｕ";
            else if (name.equals("おちゃめ"))
                return "Ｏｃｈａｍｅ";
            else if (name.equals("アール"))
                return "Ａｒｌｅ";
            else if (name.equals("ガスコン"))
                return "Ｇａｓｃｏｎ";
            else if (name.equals("偽ガスコン"))
                return "Ｆａｋｅ　Ｇａｓｃｏｎ";
            else if (name.equals("ドルメン"))
                return "Ｄｏｒｍｅｎ";
            else if (name.equals("クロッペン"))
                return "Ｋｌｏｐｐｅｎ";
            else if (name.equals("カブト将軍"))
                return "Ｇｅｎｅｒａｌ　Ｋａｂｕｔｏ";
            else if (name.equals("ボイダー将軍"))
                return "Ｇｅｎｅｒａｌ　Ｖｏｉｄｅｒ";
            else if (name.equals("ネシア将軍"))
                return "Ｇｅｎｅｒａｌ　Ｎｅｓｉａ";

            else if (name.equals("フェアリ"))
                return "Ｆａｉｒｅｙ";
            else if (name.equals("陣風"))
                return "Ｊｉｎｐｕｕ";
            else if (name.equals("瑞雲"))
                return "Ｚｕｉｕｎ";
            else if (name.equals("ヘルルーガ"))
                return "Ｈｅｒｌｕｇａ";
            else if (name.equals("ヴォート"))
                return "Ｖｏｕｇｈｔ";
            else if (name.equals("ジーク"))
                return "Ｓｉｅｇ";
            else if (name.equals("サリー"))
                return "Ｓａｌｌｙ";
            else if (name.equals("レジアーネ"))
                return "Ｒｅｇｇｉａｎｅ";

            else if (name.equals("ヌビア教団員"))
                return "Ｎｕｂｉａ　ｃｕｌｔｉｓｔ";
            else if (name.equals("ロングー星兵"))
                return "Ｌｏｎｇｏｏ　ｓｏｌｄｉｅｒ";
            else if (name.equals("ロングー星士官"))
                return "Ｌｏｎｇｏｏ　ｏｆｆｉｃｅｒ";
            else if (name.equals("ゴワハンド星兵"))
                return "Ｇｏｗａｈａｎｄ　ｓｏｌｄｉｅｒ";
            else if (name.equals("ゴワハンド星士官"))
                return "Ｇｏｗａｈａｎｄ　ｏｆｆｉｃｅｒ";
            else if (name.equals("連邦兵"))
                return "Ｆｅｄｅｒａｔｉｏｎ　ｓｏｌｄｉｅｒ";
            else if (name.equals("連邦士官"))
                return "Ｆｅｄｅｒａｔｉｏｎ　ｏｆｆｉｃｅｒ";
            else if (name.equals("ジオン兵"))
                return "Ｚｅｏｎ　ｓｏｌｄｉｅｒ";
            else if (name.equals("ジオン士官"))
                return "Ｚｅｏｎ　ｏｆｆｉｃｅｒ";
            else if (name.equals("反乱兵"))
                return "Ｒｅｂｅｌ";
            else if (name.equals("ポセイダル兵"))
                return "Ｐｏｓｅｉｄａｌ　ｓｏｌｄｉｅｒ";
            else if (name.equals("レジスタンス"))
                return "Ｒｅｓｉｓｔａｎｃｅ";
            else if (name.equals("グラドス兵"))
                return "Ｇｒａｄｏｓ　ｓｏｌｄｉｅｒ";
            else if (name.equals("連合兵"))
                return "Ａｌｌｉａｎｃｅ　ｓｏｌｄｉｅｒ";
            else if (name.equals("ギガノス兵"))
                return "Ｇｉｇａｎｏｓ　ｓｏｌｄｉｅｒ";
            else if (name.equals("ゾルダグ兵"))
                return "Ｚｏｌｄａｇ　ｓｏｌｄｉｅｒ";
            else if (name.equals("アークダーマ"))
                return "Ａｋｕｄａｍａ";
            else if (name.equals("恐竜兵"))
                return "Ｄｉｎｏｓａｕｒ　ｓｏｌｄｉｅｒ";
            else if (name.equals("シンジケート構成員"))
                return "Ｓｙｎｄｉｃａｔｅ　ｍｅｍｂｅｒ";
            else if (name.equals("治安隊"))
                return "Ｓｅｃｕｒｉｔｙ";
            else if (name.equals("村人"))
                return "Ｖｉｌｌａｇｅｒ"; 
            else if (name.equals("鉄仮面兵"))
                return "Ｉｒｏｎ　Ｍａｓｋ　ｓｏｌｄｉｅｒ";
            else if (name.equals("ザール兵"))
                return "Ｚａｒｌ　ｓｏｌｄｉｅｒ";
            else if (name.equals("民間人"))
                return "Ｃｉｖｉｌｉａｎ";
            else if (name.equals("グラドス士官"))
                return "Ｇｒａｄｏｓ　ｏｆｆｉｃｅｒ";
            else if (name.equals("ギガノス士官"))
                return "Ｇｉｇａｎｏｓ　ｏｆｆｉｃｅｒ";
            else if (name.equals("ムゲ兵"))
                return "Ｍｕｇｅ　ｓｏｌｄｉｅｒ";
            else if (name.equals("ムゲ士官"))
                return "Ｍｕｇｅ　ｏｆｆｉｃｅｒ";

            return name;
        }

        public byte[] getBytes(int num_lines){
            byte[] rawHex = new byte[1];

            int size = 0;

            byte[] starting_opcode = getStartingOpcode(); // Used in both male and female
            byte[] name = getSpeakerBytes();

            if (!male.isEmpty() || !female.isEmpty()){  // Write both the male and female parts
                // Do the male part
                byte[] male_hex = getDividedDialogues(male, num_lines, starting_opcode, name);

                // Do the female part
                byte[] female_hex = getDividedDialogues(female, num_lines, starting_opcode, name);

                // The first portrait opcode in each branch MUST be written exactly as it was at the beginning
                // of the original dialogue.
                // Why? Because if the line was voiced, you want the voiceclip to play no matter what route you're in
                // AND you don't want the same voice playing in dialogue that extends the original...
                // That's why the divided text will skip the voices if they're present.

                // Determine the size of the returned bytes
                size += opcodes.length; // This includes the first portrait opcode

                size += 4;  // The start branch for male route
                size += name.length;    // The speaker!!
                size += 2;  // The "KK" after the speaker name
                size += male_hex.length;
                size += 2;  // The end branch for male route

                size += 4;  // The start branch for female route
                size += starting_opcode.length;  // We have to write the first portrait opcode again

                size += name.length;    // The speaker!!
                size += 2;  // The "KK" after the speaker name
                size += female_hex.length;
                size += 2;  // The end branch for female route

                rawHex = new byte[size];
                int offset = 0;

                // Write the bytes
                // Branch opcodes have to be writen right before the portrait opcode (70 34 or 70 35)
                // That means the first branch is tricky: we have to write the contents of the opcodes
                // up until we find the 70 34 or 70 35, write the branch opcode and then write
                // the intended portrait opcode
                for (int i = 0; i < opcodes.length - starting_opcode.length; i++)
                    rawHex[i] = opcodes[i];

                offset += opcodes.length - starting_opcode.length;

                // Write the branch-male opcode
                rawHex[offset] = 0x70;
                rawHex[offset + 1] = (byte) (0xc2 & 0xff);
                rawHex[offset + 2] = 0x00;
                rawHex[offset + 3] = 0x00;

                offset += 4;

                // Write the starting opcode
                for (int i = 0; i < starting_opcode.length; i++)
                    rawHex[offset + i] = starting_opcode[i];

                offset += starting_opcode.length;

                // Write the speaker name
                for (int i = 0; i < name.length; i++)
                    rawHex[offset + i] = name[i];

                offset += name.length;

                // Write "KK" after the speaker name
                rawHex[offset] = 0x4b;
                rawHex[offset + 1] = 0x4b;

                offset += 2;

                // Now we write the text, divided or not
                for (int i = 0; i < male_hex.length; i++)
                    rawHex[offset + i] = male_hex[i];

                offset += male_hex.length;

                // Write the end of the branch!
                rawHex[offset] = 0x70;
                rawHex[offset + 1] = (byte) (0xf7 & 0xff);

                offset += 2;

                // Once we get to the female part, we have to write the same first portrait opcode
                // after the branch female one

                // Write the branch-female opcode
                rawHex[offset] = 0x70;
                rawHex[offset + 1] = (byte) (0xc2 & 0xff);
                rawHex[offset + 2] = 0x00;
                rawHex[offset + 3] = 0x01;  // This indicates it's female

                offset += 4;

                // Write the starting opcode again
                for (int i = 0; i < starting_opcode.length; i++)
                    rawHex[offset + i] = starting_opcode[i];

                offset += starting_opcode.length;

                // Write the speaker name
                for (int i = 0; i < name.length; i++)
                    rawHex[offset + i] = name[i];

                offset += name.length;

                // Write "KK" after the speaker name
                rawHex[offset] = 0x4b;
                rawHex[offset + 1] = 0x4b;

                offset += 2;

                // Now we write the text, divided or not
                for (int i = 0; i < female_hex.length; i++)
                    rawHex[offset + i] = female_hex[i];

                offset += female_hex.length;

                // Write the end of the branch!
                rawHex[offset] = 0x70;
                rawHex[offset + 1] = (byte) (0xf7 & 0xff);

                // Done. No need to increase the offset again.
            }
            else{   // Write the common part
                byte[] common_hex = getDividedDialogues(common, num_lines, starting_opcode, name);

                size += opcodes.length;
                size += name.length;    // The speaker!!
                size += 2;  // The "KK" after the name
                size += common_hex.length;

                rawHex = new byte[size];

                int offset = 0;

                for (int i = 0; i < opcodes.length; i++)
                    rawHex[offset + i] = opcodes[i];

                offset += opcodes.length;

                for (int i = 0; i < name.length; i++)
                    rawHex[offset + i] = name[i];

                offset += name.length;

                rawHex[offset] = 0x4b;
                rawHex[offset + 1] = 0x4b;

                offset += 2;

                for (int i = 0; i < common_hex.length; i++)
                    rawHex[offset + i] = common_hex[i];

                // Done.
            }

            return rawHex;
        }

        private byte[] getDividedDialogues(String s, int num_lines, byte[] opcode, byte[] name){
            byte[] rawHex = new byte[1];

            String[] lines = s.split("\n");
            byte[][] hex_lines = new byte[lines.length][];
            int size = 0;

            try {   // Get the hex version of the text
                for (int i = 0; i < hex_lines.length; i++){
                    hex_lines[i] = lines[i].getBytes(font_encoding);

                    // Make sure the line has an even number of bytes (there could be trouble otherwise)
                    int odd_byte = hex_lines[i].length % 2;

                    if (odd_byte != 0){
                        byte[] even_line = new byte[ hex_lines[i].length + 1 ];
                        for (int j = 0; j < hex_lines[i].length; j++)   // Copy the odd line
                            even_line[j] = hex_lines[i][j];

                        // Add an empty space at the end
                        even_line[even_line.length - 1] = 0x20;

                        hex_lines[i] = even_line;
                    }

                    size += hex_lines[i].length;
                    size += 2;  // There's either a "KK" or an "EE" after each line
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Dialogue.class.getName()).log(Level.SEVERE, null, ex);
            }

            int offset = 0;

            if (lines.length > num_lines){  // We have to write several dialogues
                // We have to write the starting opcode several times
                // However, we have to avoid repeating the voiceclips, if there's one present
                int opcode_size = 6;
                if (remote)
                    opcode_size += 2;

                byte[] new_opcode = new byte[opcode_size];

                new_opcode[0] = opcode[0];
                new_opcode[1] = opcode[1];
                new_opcode[2] = opcode[2];
                new_opcode[3] = opcode[3];
                new_opcode[4] = opcode[4];
                new_opcode[5] = opcode[5];

                if (remote){
                    new_opcode[6] = 0x54;
                    new_opcode[7] = 0x54;
                }
                // Opcode ready

                // Determine the amount of dialogues
                int num_dialogues = lines.length / num_lines;
                if (lines.length % num_lines > 0)
                    num_dialogues++;

                // Every dialogue adds the size of an opcode to the total size
                size += num_dialogues * new_opcode.length;

                // Also, every dialogue has a name followed by "KK", except the first one
                size += (num_dialogues - 1) * (name.length + 2);

                // Now we know the total size of the hex and can write on it
                rawHex = new byte[size];
                int counter = 0;

                while (counter < num_dialogues){
                    if (counter != 0){  // Write the new opcode for all dialogues except the first one
                        for (int i = 0; i < new_opcode.length; i++)
                            rawHex[offset + i] = new_opcode[i];

                        offset += new_opcode.length;

                        // And the name!!
                        for (int i = 0; i < name.length; i++)
                            rawHex[offset + i] = name[i];

                        offset += name.length;

                        // Write "KK" after the speaker name
                        rawHex[offset] = 0x4b;
                        rawHex[offset + 1] = 0x4b;

                        offset += 2;
                    }

                    if (counter == num_dialogues - 1){  // Last dialogue
                        int remaining_lines = hex_lines.length - counter*num_lines;

                        int num = 0;

                        for (int i = counter * num_dialogues; num < remaining_lines; i++){
                            for (int j = 0; j < hex_lines[i].length; j++){
                                rawHex[offset + j] = hex_lines[i][j];
                            }

                            offset += hex_lines[i].length;

                            if (num == remaining_lines - 1){  // Last line
                                rawHex[offset] = 0x45;
                                rawHex[offset + 1] = 0x45;
                            }
                            else{   // Write end of line
                                rawHex[offset] = 0x4b;
                                rawHex[offset + 1] = 0x4b;
                            }

                            offset += 2;
                            num++;
                        }
                    }
                    else{
                        int num = 0;

                        for (int i = counter * num_dialogues; num < num_lines; i++){
                            for (int j = 0; j < hex_lines[i].length; j++){
                                rawHex[offset + j] = hex_lines[i][j];
                            }

                            offset += hex_lines[i].length;

                            if (num == num_lines - 1){  // Last line
                                rawHex[offset] = 0x45;
                                rawHex[offset + 1] = 0x45;
                            }
                            else{   // Write end of line
                                rawHex[offset] = 0x4b;
                                rawHex[offset + 1] = 0x4b;
                            }

                            offset += 2;
                            num++;
                        }
                    }

                    counter++;
                }

            }
            else{   // Write just one. The name isn't used here.
                rawHex = new byte[size];

                for (int i = 0; i < hex_lines.length; i++){
                    for (int j = 0; j < hex_lines[i].length; j++){
                        rawHex[offset + j] = hex_lines[i][j];
                    }

                    offset += hex_lines[i].length;

                    if (i == hex_lines.length - 1){  // Last line
                        rawHex[offset] = 0x45;
                        rawHex[offset + 1] = 0x45;
                    }
                    else{   // Write end of line
                        rawHex[offset] = 0x4b;
                        rawHex[offset + 1] = 0x4b;
                    }

                    offset += 2;
                }
            }

            return rawHex;
        }

        private byte[] getSpeakerBytes(){
            byte[] hexName = new byte[1];
            
            try {
                hexName = speaker.getBytes(font_encoding);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Dialogue.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return hexName;
        }

        private byte[] getStartingOpcode(){
            byte[] opcode;
            int size = 6;   // 70 3x yy yy zz zz
                            // X = 4 or 5 (either up or down)
                            // Y = character ID
                            // Z = portrait ID
            if (voiced)
                size += 2;
            if (remote)
                size += 2;

            opcode = new byte[size];
            int start = opcodes.length - size;

            for (int i = 0; i < opcode.length; i++)
                opcode[i] = opcodes[start + i];

            return opcode;
        }
}
