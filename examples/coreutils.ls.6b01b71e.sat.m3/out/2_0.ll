; ModuleID = '/tmp/sea-det0p_4i/2_0.pp.ms.o.bc'
source_filename = "2_0.c"
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
  %or.cond.i = select i1 %_18, i1 %_14, i1 false
  br i1 %or.cond.i, label %_22, label %orig.main.exit

_22:                                              ; preds = %entry
  %_23 = icmp sge i64 %_5, %_4
  %_24 = icmp sgt i32 %_10, -1
  %or.cond3.i = select i1 %_23, i1 true, i1 %_24
  %_25 = icmp ne i32 %_11, 0
  %or.cond5.i = select i1 %or.cond3.i, i1 true, i1 %_25
  %or.cond7.i = select i1 %or.cond5.i, i1 true, i1 %_13
  br i1 %or.cond7.i, label %orig.main.exit, label %_26

_26:                                              ; preds = %_22
  %_27 = add nsw i64 %_5, %_4
  %_28 = sdiv i64 %_27, 4294967296
  %_29 = and i64 %_28, 1
  %_30 = icmp ne i64 %_29, 0
  br label %orig.main.exit

orig.main.exit:                                   ; preds = %_26, %_22, %entry
  %_31 = phi i1 [ false, %_22 ], [ false, %entry ], [ %_30, %_26 ]
  call void @verifier.assume(i1 %_31) #7
  %_32 = call i1 @nondet.bool()
  call void @verifier.assume.not(i1 %_32)
  br label %precall

precall:                                          ; preds = %orig.main.exit
  br label %sortcmp

sortcmp:                                          ; preds = %precall
  call void @seahorn.fn.enter() #7
  br i1 %_13, label %_33, label %_35

_33:                                              ; preds = %sortcmp
  %_34 = sub nsw i64 %_1, %_0
  br label %_60

_35:                                              ; preds = %sortcmp
  br i1 %_14, label %_36, label %_38

_36:                                              ; preds = %_35
  %_37 = sub nsw i64 %_3, %_2
  br label %_60

_38:                                              ; preds = %_35
  br i1 %_15, label %_39, label %_42

_39:                                              ; preds = %_38
  %_40 = sub nsw i64 %_5, %_4
  %_41 = add nsw i64 %_5, %_4
  br label %_60

_42:                                              ; preds = %_38
  br i1 %_16, label %_43, label %_46

_43:                                              ; preds = %_42
  %_44 = sub nsw i64 %_7, %_6
  %_45 = sub nsw i64 %_7, %_6
  br label %_60

_46:                                              ; preds = %_42
  br i1 %_17, label %_47, label %_60

_47:                                              ; preds = %_46
  %_48 = and i32 %_9, 61440
  %_49 = icmp eq i32 %_48, 16384
  %_50 = zext i1 %_49 to i64
  %_51 = and i32 %_8, 61440
  %_52 = icmp eq i32 %_51, 16384
  %.neg.i = sext i1 %_52 to i64
  %_53 = add nsw i64 %.neg.i, %_50
  %_54 = and i32 %_9, 61440
  %_55 = icmp eq i32 %_54, 16384
  %_56 = zext i1 %_55 to i64
  %_57 = and i32 %_8, 61440
  %_58 = icmp eq i32 %_57, 16384
  %.neg8.i = sext i1 %_58 to i64
  %_59 = add nsw i64 %.neg8.i, %_56
  br label %_60

_60:                                              ; preds = %_47, %_46, %_43, %_39, %_36, %_33
  %.01.i = phi i64 [ %_34, %_33 ], [ %_37, %_36 ], [ %_40, %_39 ], [ %_44, %_43 ], [ %_53, %_47 ], [ 0, %_46 ]
  %.0.i = phi i64 [ %_34, %_33 ], [ %_37, %_36 ], [ %_41, %_39 ], [ %_45, %_43 ], [ %_59, %_47 ], [ 0, %_46 ]
  %_61 = icmp eq i64 %.01.i, 0
  %spec.select12.v.i = select i1 %_19, i32 %_10, i32 %_11
  %spec.select12.i = sext i32 %spec.select12.v.i to i64
  %.23.i = select i1 %_61, i64 %spec.select12.i, i64 %.01.i
  %_62 = icmp eq i64 %.0.i, 0
  %spec.select13.v.i = select i1 %_19, i32 %_10, i32 %_11
  %spec.select13.i = sext i32 %spec.select13.v.i to i64
  %.2.i = select i1 %_62, i64 %spec.select13.i, i64 %.0.i
  %.not.i = icmp eq i64 %.23.i, 0
  br i1 %.not.i, label %_66, label %_63

_63:                                              ; preds = %_60
  %.45.i = ashr i64 %.23.i, 32
  %_64 = srem i64 %.45.i, 2
  %_65 = add nsw i64 %.45.i, %_64
  br label %_66

_66:                                              ; preds = %_63, %_60
  %.56.i = phi i64 [ %_65, %_63 ], [ %.23.i, %_60 ]
  %.not7.i = icmp eq i64 %.2.i, 0
  br i1 %.not7.i, label %_71, label %_67

_67:                                              ; preds = %_66
  %_68 = ashr i64 %.2.i, 32
  %.4.i = select i1 %_20, i64 0, i64 %_68
  %_69 = srem i64 %.4.i, 2
  %_70 = add nsw i64 %.4.i, %_69
  br label %_71

_71:                                              ; preds = %_67, %_66
  %.5.i = phi i64 [ %_70, %_67 ], [ %.2.i, %_66 ]
  %_72 = trunc i64 %.56.i to i32
  %_73 = sub nsw i32 0, %_72
  %_74 = trunc i64 %.56.i to i32
  %_75 = select i1 %_18, i32 %_73, i32 %_74
  %_76 = trunc i64 %.5.i to i32
  %_77 = sub nsw i32 0, %_76
  %_78 = trunc i64 %.5.i to i32
  %_79 = select i1 %_18, i32 %_77, i32 %_78
  %_80 = icmp eq i32 %_75, %_79
  call void @verifier.assume.not(i1 %_80)
  br label %_81

_81:                                              ; preds = %_71
  br label %verifier.error

verifier.error:                                   ; preds = %_81
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
