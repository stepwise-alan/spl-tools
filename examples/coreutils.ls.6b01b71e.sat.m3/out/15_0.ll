; ModuleID = '/tmp/sea-b_ej2_rt/15_0.pp.ms.o.bc'
source_filename = "15_0.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@llvm.used = appending global [16 x i8*] [i8* bitcast (void ()* @seahorn.fail to i8*), i8* bitcast (void (i1)* @verifier.assume to i8*), i8* bitcast (void (i1)* @verifier.assume.not to i8*), i8* bitcast (void ()* @verifier.error to i8*), i8* bitcast (void (i1)* @verifier.assume to i8*), i8* bitcast (void (i1)* @verifier.assume.not to i8*), i8* bitcast (void ()* @verifier.error to i8*), i8* bitcast (void ()* @seahorn.fail to i8*), i8* bitcast (void (i1)* @verifier.assume to i8*), i8* bitcast (void (i1)* @verifier.assume.not to i8*), i8* bitcast (void ()* @verifier.error to i8*), i8* bitcast (void ()* @seahorn.fail to i8*), i8* bitcast (void (i1)* @verifier.assume to i8*), i8* bitcast (void (i1)* @verifier.assume.not to i8*), i8* bitcast (void ()* @verifier.error to i8*), i8* bitcast (void ()* @seahorn.fail to i8*)], section "llvm.metadata"

declare i64 @_nd_st_size1() local_unnamed_addr #0

declare i64 @_nd_st_size2() local_unnamed_addr #0

declare i64 @_nd_st_atim_tv_sec1() local_unnamed_addr #0

declare i64 @_nd_st_atim_tv_sec2() local_unnamed_addr #0

declare i64 @_nd_st_ctim_tv_sec1() local_unnamed_addr #0

declare i64 @_nd_st_ctim_tv_sec2() local_unnamed_addr #0

declare i64 @_nd_st_mtim_tv_sec1() local_unnamed_addr #0

declare i64 @_nd_st_mtim_tv_sec2() local_unnamed_addr #0

declare i32 @_nd_st_mode1() local_unnamed_addr #0

declare i32 @_nd_st_mode2() local_unnamed_addr #0

declare i32 @_nd_strcoll12() local_unnamed_addr #0

declare i32 @_nd_strcmp12() local_unnamed_addr #0

declare i32 @_nd_all_fmt() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_size() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_atime() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_ctime() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_mtime() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_dir() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_reverse() local_unnamed_addr #0

declare zeroext i1 @_nd_CONFIG_LOCALE_SUPPORT() local_unnamed_addr #0

declare zeroext i1 @_nd_CONFIG_LFS() local_unnamed_addr #0

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @verifier.assume(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @verifier.assume.not(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @seahorn.fail() #1

; Function Attrs: inaccessiblememonly nofree norecurse noreturn nounwind
declare void @verifier.error() #2

; Function Attrs: inaccessiblememonly
declare void @seahorn.fn.enter() local_unnamed_addr #3

declare i32 @verifier.nondet.0()

; Function Attrs: nounwind uwtable
define dso_local i32 @main() local_unnamed_addr #4 {
entry:
  call void @seahorn.fn.enter() #7
  %_0 = call i64 @_nd_st_size1() #7
  %_1 = call i64 @_nd_st_size2() #7
  %_2 = call i64 @_nd_st_atim_tv_sec1() #7
  %_3 = call i64 @_nd_st_atim_tv_sec2() #7
  %_4 = call i64 @_nd_st_ctim_tv_sec1() #7
  %_5 = call i64 @_nd_st_ctim_tv_sec2() #7
  %_6 = call i64 @_nd_st_mtim_tv_sec1() #7
  %_7 = call i64 @_nd_st_mtim_tv_sec2() #7
  %_8 = call i32 @_nd_st_mode1() #7
  %_9 = call i32 @_nd_st_mode2() #7
  %_10 = call i32 @_nd_strcoll12() #7
  %_11 = call i32 @_nd_strcmp12() #7
  %_12 = call i32 @_nd_all_fmt() #7
  %_13 = call zeroext i1 @_nd_sort_size() #7
  %_14 = call zeroext i1 @_nd_sort_atime() #7
  %_15 = call zeroext i1 @_nd_sort_ctime() #7
  %_16 = call zeroext i1 @_nd_sort_mtime() #7
  %_17 = call zeroext i1 @_nd_sort_dir() #7
  %_18 = call zeroext i1 @_nd_sort_reverse() #7
  %_19 = call zeroext i1 @_nd_CONFIG_LOCALE_SUPPORT() #7
  %_20 = call zeroext i1 @_nd_CONFIG_LFS() #7
  %_21 = xor i1 %_20, true
  %spec.select.i = select i1 %_19, i1 %_21, i1 false
  call void @verifier.assume(i1 %spec.select.i) #7
  call void @verifier.assume(i1 %_19) #7
  call void @verifier.assume(i1 %_19) #7
  call void @verifier.assume(i1 %_19) #7
  call void @verifier.assume(i1 %_19) #7
  call void @verifier.assume(i1 %_19) #7
  call void @verifier.assume(i1 %_19) #7
  call void @verifier.assume(i1 %_19) #7
  call void @verifier.assume(i1 %_19) #7
  call void @verifier.assume(i1 %_19) #7
  call void @verifier.assume(i1 %_19) #7
  call void @verifier.assume(i1 %_19) #7
  call void @verifier.assume(i1 %_19) #7
  call void @verifier.assume(i1 %_19) #7
  call void @verifier.assume(i1 %_19) #7
  %or.cond.i = select i1 %_16, i1 %_18, i1 false
  %or.cond.not.i = xor i1 %or.cond.i, true
  %_22 = icmp ne i32 %_11, 0
  %or.cond3.i = select i1 %or.cond.not.i, i1 true, i1 %_22
  %or.cond5.i = select i1 %or.cond3.i, i1 true, i1 %_15
  %or.cond7.i = select i1 %or.cond5.i, i1 true, i1 %_14
  %or.cond9.i = select i1 %or.cond7.i, i1 true, i1 %_13
  %_23 = icmp eq i32 %_10, 0
  %not.or.cond9.i = xor i1 %or.cond9.i, true
  %spec.select10.i = select i1 %not.or.cond9.i, i1 %_23, i1 false
  call void @verifier.assume(i1 %spec.select10.i) #7
  %_24 = call i1 @nondet.bool()
  call void @verifier.assume.not(i1 %_24)
  br label %precall

precall:                                          ; preds = %entry
  br label %sortcmp

sortcmp:                                          ; preds = %precall
  call void @seahorn.fn.enter() #7
  br i1 %_13, label %_25, label %_27

_25:                                              ; preds = %sortcmp
  %_26 = sub nsw i64 %_1, %_0
  br label %_52

_27:                                              ; preds = %sortcmp
  br i1 %_14, label %_28, label %_30

_28:                                              ; preds = %_27
  %_29 = sub nsw i64 %_3, %_2
  br label %_52

_30:                                              ; preds = %_27
  br i1 %_15, label %_31, label %_34

_31:                                              ; preds = %_30
  %_32 = sub nsw i64 %_5, %_4
  %_33 = add nsw i64 %_5, %_4
  br label %_52

_34:                                              ; preds = %_30
  br i1 %_16, label %_35, label %_38

_35:                                              ; preds = %_34
  %_36 = sub nsw i64 %_7, %_6
  %_37 = sub nsw i64 %_7, %_6
  br label %_52

_38:                                              ; preds = %_34
  br i1 %_17, label %_39, label %_52

_39:                                              ; preds = %_38
  %_40 = and i32 %_9, 61440
  %_41 = icmp eq i32 %_40, 16384
  %_42 = zext i1 %_41 to i64
  %_43 = and i32 %_8, 61440
  %_44 = icmp eq i32 %_43, 16384
  %.neg.i = sext i1 %_44 to i64
  %_45 = add nsw i64 %.neg.i, %_42
  %_46 = and i32 %_9, 61440
  %_47 = icmp eq i32 %_46, 16384
  %_48 = zext i1 %_47 to i64
  %_49 = and i32 %_8, 61440
  %_50 = icmp eq i32 %_49, 16384
  %.neg8.i = sext i1 %_50 to i64
  %_51 = add nsw i64 %.neg8.i, %_48
  br label %_52

_52:                                              ; preds = %_39, %_38, %_35, %_31, %_28, %_25
  %.01.i = phi i64 [ %_26, %_25 ], [ %_29, %_28 ], [ %_32, %_31 ], [ %_36, %_35 ], [ %_45, %_39 ], [ 0, %_38 ]
  %.0.i = phi i64 [ %_26, %_25 ], [ %_29, %_28 ], [ %_33, %_31 ], [ %_37, %_35 ], [ %_51, %_39 ], [ 0, %_38 ]
  %_53 = icmp eq i64 %.01.i, 0
  %spec.select12.v.i = select i1 %_19, i32 %_10, i32 %_11
  %spec.select12.i = sext i32 %spec.select12.v.i to i64
  %.23.i = select i1 %_53, i64 %spec.select12.i, i64 %.01.i
  %_54 = icmp eq i64 %.0.i, 0
  %spec.select13.v.i = select i1 %_19, i32 %_10, i32 %_11
  %spec.select13.i = sext i32 %spec.select13.v.i to i64
  %.2.i = select i1 %_54, i64 %spec.select13.i, i64 %.0.i
  %.not.i = icmp eq i64 %.23.i, 0
  br i1 %.not.i, label %_58, label %_55

_55:                                              ; preds = %_52
  %.45.i = ashr i64 %.23.i, 32
  %_56 = srem i64 %.45.i, 2
  %_57 = add nsw i64 %.45.i, %_56
  br label %_58

_58:                                              ; preds = %_55, %_52
  %.56.i = phi i64 [ %_57, %_55 ], [ %.23.i, %_52 ]
  %.not7.i = icmp eq i64 %.2.i, 0
  br i1 %.not7.i, label %_63, label %_59

_59:                                              ; preds = %_58
  %_60 = ashr i64 %.2.i, 32
  %.4.i = select i1 %_20, i64 0, i64 %_60
  %_61 = srem i64 %.4.i, 2
  %_62 = add nsw i64 %.4.i, %_61
  br label %_63

_63:                                              ; preds = %_59, %_58
  %.5.i = phi i64 [ %_62, %_59 ], [ %.2.i, %_58 ]
  %_64 = trunc i64 %.56.i to i32
  %_65 = sub nsw i32 0, %_64
  %_66 = trunc i64 %.56.i to i32
  %_67 = select i1 %_18, i32 %_65, i32 %_66
  %_68 = trunc i64 %.5.i to i32
  %_69 = sub nsw i32 0, %_68
  %_70 = trunc i64 %.5.i to i32
  %_71 = select i1 %_18, i32 %_69, i32 %_70
  %_72 = icmp eq i32 %_67, %_71
  call void @verifier.assume.not(i1 %_72)
  br label %_73

_73:                                              ; preds = %_63
  br label %verifier.error

verifier.error:                                   ; preds = %_73
  call void @seahorn.fail()
  br label %verifier.error.split

verifier.error.split:                             ; preds = %verifier.error
  ret i32 42
}

declare i1 @nondet.bool()

declare i64 @verifier.nondet.sym.bound()

declare void @shadow.mem.load(i32, i32, i8*)

declare void @shadow.mem.trsfr.load(i32, i32, i8*)

declare i32 @shadow.mem.store(i32, i32, i8*)

declare i32 @shadow.mem.global.init(i32, i32, i8*)

declare i32 @shadow.mem.init(i32, i8*)

declare i32 @shadow.mem.arg.init(i32, i8*)

declare void @shadow.mem.arg.ref(i32, i32, i32, i8*)

declare i32 @shadow.mem.arg.mod(i32, i32, i32, i8*)

declare i32 @shadow.mem.arg.new(i32, i32, i32, i8*)

declare void @shadow.mem.in(i32, i32, i32, i8*)

declare void @shadow.mem.out(i32, i32, i32, i8*)

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @verifier.assert(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @verifier.assert.not(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare i1 @sea.is_dereferenceable(i8* nocapture, i64) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @sea.assert.if(i1, i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @sea.synth.assume(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @sea.synth.assert(i1) #1

; Function Attrs: nofree norecurse nounwind readonly
declare i1 @sea.is_modified(i8* nocapture) #5

; Function Attrs: nofree norecurse nounwind
declare void @sea.reset_modified(i8* nocapture) #6

; Function Attrs: nofree norecurse nounwind readonly
declare i1 @sea.is_read(i8* nocapture) #5

; Function Attrs: nofree norecurse nounwind
declare void @sea.reset_read(i8* nocapture) #6

; Function Attrs: nofree norecurse nounwind readonly
declare i1 @sea.is_alloc(i8* nocapture) #5

; Function Attrs: nofree norecurse nounwind
declare void @sea.tracking_on() #6

; Function Attrs: nofree norecurse nounwind
declare void @sea.tracking_off() #6

; Function Attrs: nofree norecurse nounwind
declare void @sea.free(i8* nocapture) #6

; Function Attrs: nofree norecurse nounwind
declare void @sea.set_shadowmem(i8, i8* nocapture, i8) #6

; Function Attrs: nofree norecurse nounwind readonly
declare i8 @sea.get_shadowmem(i8, i8* nocapture) #5

attributes #0 = { "frame-pointer"="none" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #1 = { inaccessiblememonly nofree norecurse nounwind }
attributes #2 = { inaccessiblememonly nofree norecurse noreturn nounwind }
attributes #3 = { inaccessiblememonly }
attributes #4 = { nounwind uwtable "frame-pointer"="none" "min-legal-vector-width"="0" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #5 = { nofree norecurse nounwind readonly }
attributes #6 = { nofree norecurse nounwind }
attributes #7 = { nounwind }

!llvm.module.flags = !{!0, !1, !2, !3}
!llvm.ident = !{!4}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 7, !"PIC Level", i32 2}
!2 = !{i32 7, !"PIE Level", i32 2}
!3 = !{i32 7, !"uwtable", i32 1}
!4 = !{!"Ubuntu clang version 14.0.0-1ubuntu1"}
