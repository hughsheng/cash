package com.tl.tplus.base.rx;



import com.tl.tplus.base.api.bean.BaseResultBean;
import com.tl.tplus.base.api.bean.ResultBean;
import com.tl.tplus.base.exception.ServerApiException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by linfp on 2016/6/20.
 * 封装rxjava一些的常用操作
 */
@SuppressWarnings( {"unchecked", "unused"})
public final class SchedulersCompat {
  private static final Observable.Transformer computationTransformer =
      new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
          return ((Observable) observable).subscribeOn(Schedulers.computation())
              .unsubscribeOn(Schedulers.computation())
              .observeOn(AndroidSchedulers.mainThread());
        }
      };

  private static final Observable.Transformer newTransformer = new Observable.Transformer() {
    @Override
    public Object call(Object observable) {
      return ((Observable) observable).subscribeOn(Schedulers.newThread())
          .unsubscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread());
    }
  };
  private static final Observable.Transformer trampolineTransformer = new Observable.Transformer() {
    @Override
    public Object call(Object observable) {
      return ((Observable) observable).subscribeOn(Schedulers.trampoline())
          .unsubscribeOn(Schedulers.trampoline())
          .observeOn(AndroidSchedulers.mainThread());
    }
  };
  private static final Observable.Transformer executorTransformer = new Observable.Transformer() {
    @Override
    public Object call(Object observable) {
      return ((Observable) observable).subscribeOn(Schedulers.from(ExecutorManager.eventExecutor))
          .unsubscribeOn(Schedulers.from(ExecutorManager.eventExecutor))
          .observeOn(AndroidSchedulers.mainThread());
    }
  };
  private static final Observable.Transformer ioTransformer = new Observable.Transformer() {
    @Override
    public Object call(final Object observable) {
      return ((Observable) observable)
          .flatMap(new Func1<Object, Object>() {
            @Override
            public Object call(Object object) {
              if (object instanceof BaseResultBean) {
                BaseResultBean temp = (BaseResultBean) object;
                int result = temp.getResult();
                String message = temp.getMessage();
                if (result != 0) {
                  return Observable.error(new ServerApiException(result, message));
                } else {
                  return Observable.just(object);
                }
              }  else {
                return Observable.error(new ServerApiException(-1, "服务器数据正在维护"));
              }
            }
          })
          .subscribeOn(Schedulers.io())
          .unsubscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread());
    }
  };

  private static final Observable.Transformer ioNoMainTransformer = new Observable.Transformer() {
    @Override
    public Object call(final Object observable) {
      return ((Observable) observable)
          .flatMap(new Func1<Object, Object>() {
            @Override
            public Object call(Object object) {
              if (object instanceof ResultBean) {
                ResultBean temp = (ResultBean) object;
                int result = temp.getResult();
                String message = temp.getMessage();
                if (result != 0) {
                  return Observable.error(new ServerApiException(result, message));
                } else {
                  return Observable.just(temp.getData());
                }

              }  else {
                return Observable.error(new ServerApiException(-1, "服务器数据正在维护"));
              }
            }
          })
          .subscribeOn(Schedulers.io())
          .unsubscribeOn(Schedulers.io());
    }
  };

  /**
   * Don't break the chain: use RxJava's compose() operator
   */
  @SuppressWarnings({"unchecked", "unused"})
  public static <T> Observable.Transformer<T, T> applyComputationSchedulers() {
    return (Observable.Transformer<T, T>) computationTransformer;
  }

  @SuppressWarnings({"unchecked", "unused"})
  public static <T> Observable.Transformer<T, T> applyIoSchedulers() {
    return (Observable.Transformer<T, T>) ioTransformer;
  }

  @SuppressWarnings({"unchecked", "unused"})
  public static <T> Observable.Transformer<T, T> applyIoNoMainSchedulers() {
    return (Observable.Transformer<T, T>) ioNoMainTransformer;
  }

  @SuppressWarnings({"unchecked", "unused"})
  public static <T> Observable.Transformer<T, T> applyNewSchedulers() {
    return (Observable.Transformer<T, T>) newTransformer;
  }

  @SuppressWarnings({"unchecked", "unused"})
  public static <T> Observable.Transformer<T, T> applyTrampolineSchedulers() {
    return (Observable.Transformer<T, T>) trampolineTransformer;
  }

  @SuppressWarnings({"unchecked", "unused"})
  public static <T> Observable.Transformer<T, T> applyExecutorSchedulers() {
    return (Observable.Transformer<T, T>) executorTransformer;
  }
}
