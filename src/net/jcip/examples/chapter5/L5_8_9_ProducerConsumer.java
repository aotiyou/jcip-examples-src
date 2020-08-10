package net.jcip.examples.chapter5;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * ProducerConsumer
 * <p/>
 * Producer and consumer tasks in a desktop search application
 * 5-8 桌面搜索应用程序中的生产者和消费者任务
 *
 * @author Brian Goetz and Tim Peierls
 */
public class L5_8_9_ProducerConsumer {

    /**
     * 生产者--文件遍历
     */
    static class FileCrawler implements Runnable {
        private final BlockingDeque<File> fileQueue;
        private final FileFilter fileFilter;
        private final File root;

        public FileCrawler(BlockingDeque<File> fileQueue,
                           FileFilter fileFilter,
                           File root){
            this.fileQueue = fileQueue;
            this.root = root;

            // 第一种写法
//            this.fileFilter = new FileFilter() {
//                @Override
//                public boolean accept(File f) {
//                    return f.isDirectory() || fileFilter.accept(f);
//                }
//            };

            // 第二种写法
//            this.fileFilter = (f) -> {return f.isDirectory() || fileFilter.accept(f);};

            // 第三种写法
            this.fileFilter = (f) -> f.isDirectory() || fileFilter.accept(f);
        }

        private boolean alreadyIndexed(File f) {
            return false;
        }

        @Override
        public void run() {
            try {
                crawl(root);
            }catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        /**
         * 生产者
         */
        private void crawl(File root) throws InterruptedException {
            File[] entries = root.listFiles(fileFilter);
            if(entries != null) {
                for(File entry : entries)
                    if(entry.isDirectory())
                        crawl(entry);
                    else if (!alreadyIndexed(entry))
                        fileQueue.put(entry);
            }
        }
    }

    /**
     * 消费者--建立索引
     */
    static class Indexer implements Runnable {

        private final BlockingDeque<File> queue;

        public Indexer(BlockingDeque<File> queue){
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while(true)
                    indexFile(queue.take());
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }

        /**
         * 消费者
         */
        public void indexFile(File file) {
            // Index the file...
        }
    }

    private static final int BOUND = 10;
    private static final int N_CONSUMERS = Runtime.getRuntime().availableProcessors();

    /**
     * 5-9启动桌面搜索
     * 启动了多个爬虫程序和索引建立程序
     * 消费者线程永远不会退出，因而程序无法终止
     */
    public static void startIndexing(File[] roots) {
        BlockingDeque<File> queue = new LinkedBlockingDeque<>(BOUND);
        FileFilter filter = (file) -> true;

        for(File root : roots)
            new Thread(new FileCrawler(queue, filter, root)).start();

        for(int i = 0; i < N_CONSUMERS; i++)
            new Thread(new Indexer(queue)).start();
    }

}
