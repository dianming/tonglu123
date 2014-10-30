package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.zht.index.News;

public class SolrTest {

	private static Logger logs = Logger.getLogger(SolrTest.class);
	private SolrServer server;
	private HttpSolrServer httpServer;
	private static final String DEFAULT_URL = "http://localhost:8080/solr";

	@Before
	public void init() {
		logs.debug("初始化！！");
		server = new HttpSolrServer(DEFAULT_URL);
		httpServer = new HttpSolrServer(DEFAULT_URL);
	}

	@After
	public void destory() {	
		logs.debug("测试结束！！");
		server = null;
		httpServer = null;
	}

	public final void fail(Object o) {
		logs.debug("----->对象" + o);
	}

	/**
	 * <b>function:</b> 测试是否创建server对象成功
	 */
	public void server() {
		fail(server);
		fail(httpServer);
	}

	/**
	 * one 添加索引
	 */
	public void addDoc(String fieldName, String condition) throws Exception {
		// 创建doc文档
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField(fieldName, condition);
		// 添加一个doc文档
		UpdateResponse response = server.add(doc);
		fail(server.commit());// commit后才保存到索引库
		fail(response);
		fail("query time：" + response.getQTime());
		fail("Elapsed Time：" + response.getElapsedTime());
		fail("status：" + response.getStatus());
	}

	/**
	 * multi 添加索引
	 */
	public void addDocs(Collection<SolrInputDocument> docs) throws Exception {
		UpdateResponse response = server.add(docs);
		Assert.assertEquals("添加索引失败", 0, response.getStatus());
		fail(server.commit());
	}

	/**
	 * addBean 索引
	 */
	public void addBean(Object object) throws Exception {
		UpdateResponse response = server.addBean(object);
		fail(server.commit());
		Assert.assertEquals("添加索引失败", 0, response.getStatus());
	}

	public void addBeans(List<Object> objects) throws Exception {
		UpdateResponse response = server.addBeans(objects);
		fail(server.commit());
		Assert.assertEquals("添加多个索引失败", 0, response.getStatus());
	}

	/**
	 * 移除一个或者多个
	 */
	public void removeList(List<String> listValue) throws Exception {
		server.deleteById(listValue);
		server.commit();
	}

	public void remove(String key, String value) throws Exception {
		if (key != null && !key.isEmpty()) {
			server.deleteByQuery(key + ":" + value);
		} else {
			server.deleteById(value);
		}
		// 阻塞，数据变化，刷新到磁盘
		server.commit(true, true);
		SolrDocumentList docList = query(key, value);
		Assert.assertEquals("未删除", docList.size(), 0);
	}

	public SolrDocumentList query(SolrParams solrParams) throws Exception {
		QueryResponse queryResponse = server.query(solrParams);
		return queryResponse.getResults();
	}

	// 分片
	public QueryResponse query(SolrQuery solrQuery) throws Exception {
		return server.query(solrQuery);
	}

	/**
	 * <b>function:</b> 根据query参数查询索引
	 */
	public SolrDocumentList query(String fieldName, String queryCondition)
			throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		// 以下两个参数常用作分页时使用
		// query.setRows(1);// 设置每次取多少条
		solrQuery.setStart(0);// 设置从第几条开始查询
		solrQuery.setRows(1000);
		// 以下几行设置查询结果关键字高亮显示
		solrQuery.setHighlight(true);
		// hl.fl参数表示哪个几Field关键字段高亮
		// queryBase.setQuery("occurs_status:分享");
		// 设置排序字段
		// queryBase.setSort("occurs_time",ORDER.desc);
		solrQuery.setQuery(fieldName + ":" + queryCondition);
		// server 是 HttpSolrServer 的实例
		httpServer.setParser(new XMLResponseParser());
		QueryResponse queryResponse = server.query(solrQuery);
		return queryResponse.getResults();
	}

	/**
	 * 智能补全
	 */
	@Test
	public void suggest() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.set("qt", "/suggest");
		solrQuery.set("q", "n_title:z");
		solrQuery.set("spellcheck.build", "true");
		QueryResponse response = query(solrQuery);
		logs.debug("耗时：" + response.getQTime());
		SpellCheckResponse spellCheckResponse = response
				.getSpellCheckResponse();
		if (spellCheckResponse != null) {
			List<Suggestion> suggestionList = spellCheckResponse
					.getSuggestions();
			for (Suggestion suggestion : suggestionList) {
				System.out.println("Suggestions NumFound: "
						+ suggestion.getNumFound());
				System.out.println("Token: " + suggestion.getToken());
				System.out.print("Suggested: ");
				List<String> suggestedWordList = suggestion.getAlternatives();
				for (String word : suggestedWordList) {
					System.out.println(word + ", ");
				}
				System.out.println();
			}
		}
	}

	@Test
	public void add() throws Exception {
		Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("n_content",
				"京华时报２００９年1月23日报道 昨天，受一股来自中西伯利亚的强冷空气影响，本市出现大风降温天气，白天最高气温只有零下7摄氏度，同时伴有6到7级的偏北风。");
		doc.addField("n_title", "中国天气");
		docs.add(doc);
		doc = new SolrInputDocument();
		doc.addField("n_title", "中国荷兰");
		doc.addField("n_content", "中国中国");
		docs.add(doc);
		doc = new SolrInputDocument();
		doc.addField("n_title", "中国伦敦");
		doc.addField("n_content", "中国中国中国");
		docs.add(doc);
		addDocs(docs);
		List<Object> newsList = new ArrayList<Object>();
		News news = new News();
		news.setN_title("中国北京");
		news.setN_content("中国中国中国中国");
		newsList.add(news);
		news = new News();
		news.setN_title("中国式");
		news.setN_content("中国中国中国中国中国");
		newsList.add(news);
		addBeans(newsList);
		news = new News();
		news.setN_title("国内新闻");
		news.setN_content("中国，中国，中国，中国，中国，天朝是个好地方。");
		addBean(news);
	}

	@Test
	public void del() throws Exception {
		remove(null, null);// ID删除
		List<String> listId = new ArrayList<String>();
		listId.add(null);
		removeList(listId);// 查询结果删除
	}

	@Test
	public void docBinderObject() throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		SolrDocumentList list = query(solrQuery).getResults();
		logs.debug("==>" + list.size());
		List<News> newsList = server.getBinder().getBeans(News.class, list);
		DocumentObjectBinder binder = new DocumentObjectBinder();
		List<News> newsListObject = binder.getBeans(News.class, list);
		for (News news : newsListObject) {
			logs.debug("binder---->" + news.getN_title());
		}

		SolrDocument doc = new SolrDocument();
		doc.addField("n_title", "中国游览五岳");
		doc.addField("n_content", "地大风景多。");
		DocumentObjectBinder binder1 = new DocumentObjectBinder();
		News news = new News();
		news.setN_title("五岳之首，泰山");
		news.setN_content("每逢假期都是人山人海，是全世界的特色。");
		binder.toSolrInputDocument(news);
		binder.getBean(News.class, doc);
		SolrDocumentList list1 = new SolrDocumentList();
		list1.add(doc);
		List<News> re = binder.getBeans(News.class, list1);
		logs.debug("--------------------->" + re.size());
		for (News news2 : re) {
			logs.debug(news2.getN_title());
		}

	}
	
	@Test
	public void queryParticiple() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put(CommonParams.Q, "n_content:中国");
		map.put(CommonParams.START, "0");
		map.put(CommonParams.ROWS, "10");
		// map.put(CommonParams.FQ, "n_title:美国");
		SolrParams params = new MapSolrParams(map);

//		SolrDocumentList docList1 = query("n_title", "美国");
		
		SolrQuery sq = new SolrQuery();
		sq.setQuery("*:*");
		sq.addSort("id", ORDER.desc);
		sq.addFacetQuery("n_content:中国");
		sq.setFacet(true).setFacetMinCount(0).setFacetPrefix("中")
		// .setFacetLimit(5)//段
				.addFacetField("n_content");// 分片字段
		
		QueryResponse queryResponse = query(sq);
		
		SolrDocumentList docList = queryResponse.getResults();
		List<FacetField> facetFields = queryResponse.getFacetFields();
		List<News> newsList = queryResponse.getBeans(News.class);
		FacetField facetField1 = queryResponse.getFacetField("n_content");
		List<Count> facetFields2 = facetField1.getValues();
		for (Count count : facetFields2) {
			// 关键字 - 出现次数
			fail(count.getName() + ": " + count.getCount());
		}

		for (News news : newsList) {
			logs.debug("bean---->" + news.getN_content());
		}

		for (FacetField facetField : facetFields) {
			List<Count> facetCounts = facetField.getValues();
			for (Count count : facetCounts) {
				logs.debug("facet----->" + count.getName() + ": "
						+ count.getCount());
			}
		}
		logs.debug("doc count =======>" + docList.size());
		for (SolrDocument sd : docList) {
			logs.debug("docs----->" + sd.get("n_content"));
		}
	}
	
	@Test
	public void Querydismax() throws Exception{
		
	}
}
