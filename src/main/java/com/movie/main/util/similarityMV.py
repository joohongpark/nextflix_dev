import numpy as np
import sys
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity
# content based filtering
def query_OcracleSQL(query):

    import pandas as pd
    import cx_Oracle as co
    from datetime import datetime

    conn = co.connect("java", "oracle", "192.168.10.13:1521/XE", encoding="utf-8")
    query_result = pd.read_sql(query, conn)
    conn.close()
    return query_result
   # ,  NVL(T1.SCORE_SP,0) AS SP
   # ,  NVL(T1.SCORE_AD,0) AS AD

def main(name):
    INFO = {'SELECT_INFO':"""select T1.MOVIE_CD
             , T1.MOVIE_NM
             , NVL(T1.SCORE_NT,0) AS NT      
             , T1.DR
             ,(SELECT   
                         SUBSTR(
                               XMLAGG(
                            XMLELEMENT(COL ,',', (SELECT COMM_NM
                                                        FROM COMM_CODE
                                                        WHERE COMM_CD =A.GENRE_CD)) ).EXTRACT('//text()'
                        ).GETSTRINGVAL()
                       , 2) 
                FROM GENRES A 
                WHERE MOVIE_CD = T1.MOVIE_CD
                GROUP BY MOVIE_CD ) AS GENRES_NM
        from movie_check T1"""}
    data = query_OcracleSQL(INFO.get('SELECT_INFO'))

    data['GENRES_NMS'] = data['GENRES_NM'].str.split(',').str[0]

    count_vector = CountVectorizer(ngram_range=(1, 3))
    c_vector_genres = count_vector.fit_transform(data['GENRES_NMS'].apply(lambda x: np.str_(x)))

    gerne_c_sim = cosine_similarity(c_vector_genres, c_vector_genres).argsort()[:, ::-1]

    def get_recommend_movie_list(df, movie_title, top=30):
        target_movie_index = df[df['MOVIE_NM'] == movie_title].index.values
        sim_index = gerne_c_sim[target_movie_index, :top].reshape(-1)
        sim_index = sim_index[sim_index != target_movie_index]
        result = df.iloc[sim_index].sort_values('NT', ascending=False)[:10]
        return result

    # recommender = get_recommend_movie_list(data, movie_title='Toy Story')
    recommender = get_recommend_movie_list(data, movie_title=name)

    top10 = data[data['MOVIE_CD'].isin(recommender['MOVIE_CD'])]

    print(top10['MOVIE_CD'].values.tolist())

a = ''
if len(sys.argv) > 1:
    for i, arg in enumerate(sys.argv):
        if i != 0:
            a +=arg+' '
else:
    a = "신세계"
main(a.strip())